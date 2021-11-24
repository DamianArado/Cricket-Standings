package com.example.cricket.core.team.web;

public class TeamView 
{

    private Long id;
    private String name;
    private int numPlayers;


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

    public int getNumPlayers() 
    {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) 
    {
        this.numPlayers = numPlayers;
    }
}
