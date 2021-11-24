package com.example.cricket.core.event.web;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.cricket.base.BaseRequest;

public class EventBaseReq extends BaseRequest 
{

    @NotNull
    private String name;

    @NotNull
    private String content;

    @NotEmpty
    private List<@Valid Id> players;


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

    public List<Id> getPlayers() 
    {
        return players;
    }

    public void setPlayers(List<Id> players) 
    {
        this.players = players;
    }
}
