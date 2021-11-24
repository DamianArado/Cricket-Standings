package com.example.cricket.core.coach.web;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.cricket.core.coach.Coach;
import com.example.cricket.core.coach.CoachService;

@RestController
@RequestMapping("/coach")
public class CoachController 
{

    private final CoachService service;

    public CoachController(CoachService service) 
    {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CoachView getCoach(@PathVariable Long id) 
    {
        return service.getCoach(id);
    }

    @GetMapping
    @ResponseBody
    public Page<CoachView> getAllCoaches(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) 
        Pageable pageable) 
    {
        return service.findAllCoaches(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CoachView create(@RequestBody @Valid CoachBaseReq req) 
    {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoach(@PathVariable Long id)
    {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public CoachView updateCoach(@PathVariable(name = "id") Long id,
                                 @RequestBody @Valid CoachBaseReq req) 
    {
        Coach coach = service.findCoachOrThrow(id);
        return service.update(coach, req);
    }
}
