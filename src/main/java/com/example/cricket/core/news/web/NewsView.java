package com.example.cricket.core.news.web;

import java.util.HashSet;
import java.util.Set;

import com.example.cricket.core.team.web.TeamView;

public class NewsView 
{

    private long id;

    private String name;

    private String content;

    private Set<TeamView> teams = new HashSet<>();


    public long getId() 
    {
        return id;
    }

    public void setId(long id) 
    {
        this.id = id;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getContent() 
    {
        return content;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public Set<TeamView> getTeams() 
    {
        return teams;
    }

    public void setTeams(Set<TeamView> teams) 
    { 
        this.teams = teams;
    }
}
