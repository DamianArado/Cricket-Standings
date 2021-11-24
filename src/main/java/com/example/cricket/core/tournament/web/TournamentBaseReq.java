package com.example.cricket.core.tournament.web;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.example.cricket.base.BaseRequest;

public class TournamentBaseReq extends BaseRequest implements Serializable 
{

    @NotEmpty
    private String name;

    @NotEmpty
    private String country;

    private List<@Valid Id> teams;


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

    public List<Id> getTeams() 
    {
        return teams;
    }

    public void setTeams(List<Id> teams) 
    {
        this.teams = teams;
    }
}
