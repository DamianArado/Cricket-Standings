package com.example.cricket.core.result.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.example.cricket.core.result.Result;
import com.example.cricket.core.result.web.ResultView;
import com.example.cricket.core.team.Team;
import com.example.cricket.core.team.converter.TeamToTeamViewConverter;
import com.example.cricket.core.team.web.TeamView;
import com.example.cricket.core.tournament.Tournament;
import com.example.cricket.core.tournament.converter.TournamentToTournamentViewConverter;
import com.example.cricket.core.tournament.web.TournamentView;

@Component
public class ResultToResultViewConverter implements Converter<Result, ResultView> 
{

    private final TeamToTeamViewConverter teamToTeamViewConverter;
    private final TournamentToTournamentViewConverter tournamentToTournamentViewConverter;

    public ResultToResultViewConverter(TeamToTeamViewConverter teamToTeamViewConverter,
                                       TournamentToTournamentViewConverter tournamentToTournamentViewConverter) 
    {
        this.teamToTeamViewConverter = teamToTeamViewConverter;
        this.tournamentToTournamentViewConverter = tournamentToTournamentViewConverter;
    }

    @Override
    public ResultView convert(@NonNull Result result) 
    {
        ResultView view = new ResultView();
        view.setId(result.getId());
        view.setDraw(result.getDraw());
        view.setGoals(result.getGoals());
        view.setGoalsMissed(result.getGoalsMissed());
        view.setLoses(result.getLoses());
        view.setMissed(result.getMissed());
        view.setPlace(result.getPlace());
        view.setWins(result.getWins());
        view.setPoints(result.getPoints());
        Team team = result.getTeam();
        TeamView teamView = teamToTeamViewConverter.convert(team);
        view.setTeam(teamView);
        Tournament tournament = result.getTournament();
        TournamentView tournamentView = tournamentToTournamentViewConverter.convert(tournament);
        view.setTournament(tournamentView);
        return view;
    }
}
