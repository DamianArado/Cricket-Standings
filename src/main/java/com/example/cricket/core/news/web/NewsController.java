package com.example.cricket.core.news.web;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.cricket.core.news.News;
import com.example.cricket.core.news.NewsService;

@RestController
@RequestMapping("/news")
public class NewsController 
{

    private final NewsService service;

    public NewsController(final NewsService service) 
    {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public NewsView getNews(@PathVariable Long id) 
    {
        return service.getNews(id);
    }

    @GetMapping
    @ResponseBody
    public Page<NewsView> getAllNews(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) 
        Pageable pageable) 
    {
        return service.findAllNews(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public NewsView create(@RequestBody @Valid NewsBaseReq req) 
    {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNews(@PathVariable Long id)
    {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public NewsView updateNews(@PathVariable(name = "id") Long id,
                               @RequestBody @Valid NewsBaseReq req)
    {
        News news = service.findNewsOrThrow(id);
        return service.update(news, req);
    }
}
