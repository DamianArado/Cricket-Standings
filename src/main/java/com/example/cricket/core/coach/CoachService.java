package com.example.cricket.core.coach;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.cricket.core.coach.converter.CoachToCoachViewConverter;
import com.example.cricket.core.coach.web.CoachView;
import com.example.cricket.core.coach.web.CoachBaseReq;
import com.example.cricket.core.team.TeamRepo;
import com.example.cricket.error.EntityNotFoundException;
import com.example.cricket.util.MessageUtil;

@Service
public class CoachService 
{

    private final CoachRepo coachRepo;
    private final CoachToCoachViewConverter coachToCoachViewConverter;
    private final TeamRepo teamRepo;
    private final MessageUtil messageUtil;

    public CoachService(CoachRepo coachRepo,
                        CoachToCoachViewConverter coachToCoachViewConverter,
                        TeamRepo teamRepo,
                        MessageUtil messageUtil) 
    {
        this.coachRepo = coachRepo;
        this.coachToCoachViewConverter = coachToCoachViewConverter;
        this.teamRepo = teamRepo;
        this.messageUtil = messageUtil;
    }

    public Coach findCoachOrThrow(Long id) 
    {
        return coachRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("coach.NotFound", id)));
    }

    public CoachView getCoach(Long id) 
    {
        Coach coach = findCoachOrThrow(id);
        return coachToCoachViewConverter.convert(coach);
    }

    public CoachView create(CoachBaseReq req) 
    {
        Coach coach = new Coach();
        this.prepare(coach, req);
        Coach coachSave = coachRepo.save(coach);
        return coachToCoachViewConverter.convert(coachSave);
    }

    public Page<CoachView> findAllCoaches(Pageable pageable) 
    {
        Page<Coach> coaches = coachRepo.findAll(pageable);
        List<CoachView> coachViews = new ArrayList<>();
        coaches.forEach(coach -> 
        {
            CoachView coachView = coachToCoachViewConverter.convert(coach);
            coachViews.add(coachView);
        });
        return new PageImpl<>(coachViews, pageable, coaches.getTotalElements());
    }

    @Transactional
    public void delete(Long id) 
    {
        try {
            coachRepo.deleteById(id);
        } 
        catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("coach.NotFound", id));
        }
    }

    public CoachView update(Coach coach, CoachBaseReq req) 
    {
        Coach newCoach = this.prepare(coach, req);
        Coach coachForSave = coachRepo.save(newCoach);
        return coachToCoachViewConverter.convert(coachForSave);
    }

    private Coach prepare(Coach coach, CoachBaseReq req) 
    {
        coach.setName(req.getName());
        coach.setSurname(req.getSurname());
        coach.setExperience(req.getExperience());
        coach.setAge(req.getAge());
        coach.setTeam(teamRepo.getOne(req.getTeamId()));
        return coach;
    }
}
