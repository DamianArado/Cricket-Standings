package com.example.cricket.core.tournament.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.example.cricket.core.team.Team;
import com.example.cricket.core.team.converter.TeamToTeamViewConverter;
import com.example.cricket.core.team.web.TeamView;
import com.example.cricket.core.tournament.Tournament;
import com.example.cricket.core.tournament.web.TournamentView;

@Component
public class TournamentToTournamentViewConverter implements Converter<Tournament, TournamentView> 
{

    private final TeamToTeamViewConverter teamToTeamViewConverter;

    public TournamentToTournamentViewConverter(TeamToTeamViewConverter teamToTeamViewConverter) 
    {
        this.teamToTeamViewConverter = teamToTeamViewConverter;
    }

    @Override
    public TournamentView convert(@NonNull Tournament tournament) 
    {
        TournamentView view = new TournamentView();
        view.setId(tournament.getId());
        view.setName(tournament.getName());
        view.setCountry(tournament.getCountry());
        Set<TeamView> views = new HashSet<>();
        Set<Team> teams= tournament.getTeams();
        teams.forEach(team -> views.add(teamToTeamViewConverter.convert(team)));
        view.setTeams(views);
        return view;
    }
}
