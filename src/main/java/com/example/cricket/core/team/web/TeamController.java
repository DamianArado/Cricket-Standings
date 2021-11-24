package com.example.cricket.core.team.web;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.cricket.core.team.Team;
import com.example.cricket.core.team.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController 
{

    private final TeamService service;

    public TeamController(TeamService service) 
    {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public TeamView getTeam(@PathVariable Long id) 
    {
        return service.getTeam(id);
    }

    @GetMapping
    @ResponseBody
    public Page<TeamView> getAllTeam(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) 
        Pageable pageable) 
    {
        return service.findAllTeam(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TeamView create(@RequestBody @Valid TeamBaseReq req) 
    {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeam(@PathVariable Long id)
    {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public TeamView updateTeam(@PathVariable(name = "id") Long id,
                              @RequestBody @Valid TeamBaseReq req)
    {
        Team team = service.findTeamOrThrow(id);
        return service.update(team, req);
    }
}

