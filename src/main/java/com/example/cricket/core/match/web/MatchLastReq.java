package com.example.cricket.core.match.web;

import javax.validation.constraints.NotEmpty;

public class MatchLastReq 
{

    @NotEmpty
    private String playerSurname;

    @NotEmpty
    private String playerName;


    public String getPlayerName() 
    {
        return playerName;
    }

    public void setPlayerName(String playerName) 
    {
        this.playerName = playerName;
    }

    public String getPlayerSurname() 
    {
        return playerSurname;
    }

    public void setPlayerSurname(String playerSurname) 
    {
        this.playerSurname = playerSurname;
    }
}
