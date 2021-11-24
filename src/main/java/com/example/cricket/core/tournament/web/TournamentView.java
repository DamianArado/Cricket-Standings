package com.example.cricket.core.tournament.web;

import java.io.Serializable;
import java.util.Set;

import com.example.cricket.core.team.web.TeamView;

public class TournamentView implements Serializable 
{

    private Long id;

    private String name;

    private String country;

    private Set<TeamView> teams;


    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
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

    public String getCountry() 
    {
        return country;
    }

    public void setCountry(String country) 
    {
        this.country = country;
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
