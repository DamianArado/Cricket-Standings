package com.example.cricket.core.news;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.cricket.base.BaseRequest;
import com.example.cricket.core.news.converter.NewsToNewsViewConverter;
import com.example.cricket.core.news.web.NewsView;
import com.example.cricket.core.news.web.NewsBaseReq;
import com.example.cricket.core.team.Team;
import com.example.cricket.core.team.TeamRepo;
import com.example.cricket.error.EntityNotFoundException;
import com.example.cricket.util.MessageUtil;

@Service
public class NewsService
{

    private final NewsRepo newsRepo;
    private final NewsToNewsViewConverter newsToNewsViewConverter;
    private final TeamRepo teamRepo;
    private final MessageUtil messageUtil;

    public NewsService(NewsRepo newsRepo,
                       NewsToNewsViewConverter newsToNewsViewConverter,
                       TeamRepo teamRepo,
                       MessageUtil messageUtil) 
    {
        this.newsRepo = newsRepo;
        this.newsToNewsViewConverter = newsToNewsViewConverter;
        this.teamRepo = teamRepo;
        this.messageUtil = messageUtil;
    }

    public News findNewsOrThrow(Long id) 
    {
        return newsRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("news.NotFound", id)));
    }

    public NewsView getNews(Long id) 
    {
        News news = findNewsOrThrow(id);
        return newsToNewsViewConverter.convert(news);
    }

    public Page<NewsView> findAllNews(Pageable pageable) 
    {
        Page<News> news = newsRepo.findAll(pageable);
        List<NewsView> newsViews = new ArrayList<>();
        news.forEach(n -> {
            NewsView newsView = newsToNewsViewConverter.convert(n);
            newsViews.add(newsView);
        });
        return new PageImpl<>(newsViews, pageable, news.getTotalElements());
    }

    public NewsView create(NewsBaseReq req) 
    {
        News news = new News();
        this.prepare(news,req);
        News newsSave = newsRepo.save(news);
        return newsToNewsViewConverter.convert(newsSave);
    }

    @Transactional
    public void delete(Long id) 
    {
        try {
            newsRepo.deleteById(id);
        } 
        catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("news.NotFound", id));
        }
    }

    public NewsView update(News news, NewsBaseReq req) 
    {
        News newNews = this.prepare(news,req);
        News newsSave = newsRepo.save(newNews);
        return newsToNewsViewConverter.convert(newsSave);
    }


    private News prepare(News news, NewsBaseReq req) 
    {
        news.setName(req.getName());
        news.setContent(req.getContent());
        List<Team> teamList = teamRepo.findAllById(req.getTeams()
                .stream()
                .map(BaseRequest.Id::getId)
                .collect(Collectors.toSet()));
        Set<Team> teams = new HashSet<>(teamList);
        news.setTeams(teams);
        return news;
    }
}
