package com.example.cricket.core.match.web;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.example.cricket.base.BaseRequest;


public class MatchBaseReq extends BaseRequest 
{

    @NotNull
    private Long teamOwnerId;

    @NotNull
    private Long teamGuestId;

    private int scoreOwners;

    private int scoreGuests;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date matchDate;

    @NotNull
    private Long tournamentId;


    public Long getTeamOwnerId() 
    {
        return teamOwnerId;
    }

    public void setTeamOwnerId(Long teamOwnerId) 
    {
        this.teamOwnerId = teamOwnerId;
    }

    public Long getTeamGuestId() 
    {
        return teamGuestId;
    }

    public void setTeamGuestId(Long teamGuestId) 
    {
        this.teamGuestId = teamGuestId;
    }

    public int getScoreOwners() 
    {
        return scoreOwners;
    }

    public void setScoreOwners(int scoreOwners) 
    {
        this.scoreOwners = scoreOwners;
    }

    public int getScoreGuests() 
    {
        return scoreGuests;
    }

    public void setScoreGuests(int scoreGuests) 
    {
        this.scoreGuests = scoreGuests;
    }

    public Date getMatchDate() 
    {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) 
    {
        this.matchDate = matchDate;
    }

    public Long getTournamentId() 
    {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) 
    {
        this.tournamentId = tournamentId;
    }
}
