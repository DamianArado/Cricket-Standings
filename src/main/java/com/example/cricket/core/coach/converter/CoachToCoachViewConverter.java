package com.example.cricket.core.coach.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.example.cricket.core.coach.Coach;
import com.example.cricket.core.coach.web.CoachView;
import com.example.cricket.core.team.Team;
import com.example.cricket.core.team.converter.TeamToTeamViewConverter;

@Component
public class CoachToCoachViewConverter implements Converter<Coach, CoachView> 
{

    private final TeamToTeamViewConverter teamToTeamViewConverter;

    public CoachToCoachViewConverter(TeamToTeamViewConverter teamToTeamViewConverter) 
    {
        this.teamToTeamViewConverter = teamToTeamViewConverter;
    }

    @Override
    public CoachView convert(@NonNull Coach coach) 
    {
        CoachView view = new CoachView();
        view.setId(coach.getId());
        view.setName(coach.getName());
        view.setSurname(coach.getSurname());
        view.setAge(coach.getAge());
        view.setExperience(coach.getExperience());
        Team team = coach.getTeam();
        view.setTeam(teamToTeamViewConverter.convert(team));
        return view;
    }
}
