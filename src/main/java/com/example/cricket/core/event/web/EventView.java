package com.example.cricket.core.event.web;

import java.util.HashSet;
import java.util.Set;

import com.example.cricket.core.player.web.PlayerView;


public class EventView 
{
    private long id;

    private String name;

    private String content;

    private Set<PlayerView> players = new HashSet<>();

    public long getId()
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

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Set<PlayerView> getPlayers()
    {
        return players;
    }

    public void setPlayers(Set<PlayerView> players)
    {
        this.players = players;
    }
}
