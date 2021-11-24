package com.example.cricket.core.player.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.example.cricket.core.player.Player;
import com.example.cricket.core.player.web.PlayerView;
import com.example.cricket.core.team.Team;
import com.example.cricket.core.team.converter.TeamToTeamViewConverter;
import com.example.cricket.core.team.web.TeamView;

@Component
public class PlayerToPlayerViewConverter implements Converter<Player, PlayerView> 
{

    private final TeamToTeamViewConverter teamToTeamViewConverter;

    public PlayerToPlayerViewConverter(TeamToTeamViewConverter teamToTeamViewConverter) 
    {
        this.teamToTeamViewConverter = teamToTeamViewConverter;
    }

    @Override
    public PlayerView convert(@NonNull Player player) 
    {
        PlayerView view = new PlayerView();
        view.setId(player.getId());
        view.setName(player.getName());
        view.setSurname(player.getSurname());
        view.setAge(player.getAge());
        view.setHeight(player.getHeight());
        view.setWeight(player.getWeight());
        Set<TeamView> views = new HashSet<>();
        Set<Team> teams= player.getTeams();
        teams.forEach(team -> {
            TeamView teamView = teamToTeamViewConverter.convert(team);
            views.add(teamView);
        });
        view.setTeams(views);
        return view;
    }
}
