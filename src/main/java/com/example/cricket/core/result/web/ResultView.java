package com.example.cricket.core.result.web;

import com.example.cricket.core.team.web.TeamView;
import com.example.cricket.core.tournament.web.TournamentView;

public class ResultView 
{
    private long id;

    private int place;

    private int points;

    private int wins;

    private int loses;

    private int draw;

    private int goalsMissed;

    private int goals;

    private int missed;

    private TournamentView tournament;

    private TeamView team;


    public long getId() 
    {
        return id;
    }

    public void setId(long id) 
    {
        this.id = id;
    }

    public int getPlace() 
    {
        return place;
    }

    public void setPlace(int place) 
    {
        this.place = place;
    }

    public int getPoints() 
    {
        return points;
    }

    public void setPoints(int points) 
    {
        this.points = points;
    }

    public int getWins() 
    {
        return wins;
    }

    public void setWins(int wins) 
    {
        this.wins = wins;
    }

    public int getLoses() 
    {
        return loses;
    }

    public void setLoses(int loses) 
    {
        this.loses = loses;
    }

    public int getDraw() 
    {
        return draw;
    }

    public void setDraw(int draw) 
    {
        this.draw = draw;
    }

    public int getGoalsMissed() 
    {
        return goalsMissed;
    }

    public void setGoalsMissed(int goalsMissed) 
    {
        this.goalsMissed = goalsMissed;
    }

    public int getGoals() 
    {
        return goals;
    }

    public void setGoals(int goals) 
    {
        this.goals = goals;
    }

    public int getMissed() 
    {
        return missed;
    }

    public void setMissed(int missed) 
    {
        this.missed = missed;
    }

    public TournamentView getTournament() 
    {
        return tournament;
    }

    public void setTournament(TournamentView tournament) 
    {
        this.tournament = tournament;
    }

    public TeamView getTeam() 
    {
        return team;
    }

    public void setTeam(TeamView team) 
    {
        this.team = team;
    }
}
