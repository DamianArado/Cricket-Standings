package com.example.cricket.core.tournament;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.cricket.base.BaseRequest;
import com.example.cricket.core.team.Team;
import com.example.cricket.core.team.TeamRepo;
import com.example.cricket.core.tournament.converter.TournamentToTournamentViewConverter;
import com.example.cricket.core.tournament.web.TournamentView;
import com.example.cricket.core.tournament.web.TournamentBaseReq;
import com.example.cricket.error.EntityNotFoundException;
import com.example.cricket.util.MessageUtil;

@Service
public class TournamentService 
{

    private final TournamentRepo tournamentRepo;
    private final TournamentToTournamentViewConverter tournamentToTournamentViewConverter;
    private final TeamRepo teamRepo;
    private final MessageUtil messageUtil;

    public TournamentService(TournamentRepo tournamentRepo,
                             TeamRepo teamRepo,
                             TournamentToTournamentViewConverter tournamentToTournamentViewConverter,
                             MessageUtil messageUtil) 
    {
        this.tournamentRepo = tournamentRepo;
        this.teamRepo = teamRepo;
        this.tournamentToTournamentViewConverter = tournamentToTournamentViewConverter;
        this.messageUtil = messageUtil;
    }

    public TournamentView getTournament(Long id) 
    {
        Tournament tournament = findTournamentOrThrow(id);
        return tournamentToTournamentViewConverter.convert(tournament);
    }

    public Tournament findTournamentOrThrow(Long id) 
    {
        return tournamentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("tournament.NotFound", id)));
    }

    public Page<TournamentView> findAllTournament(Pageable pageable)
    {
        Page<Tournament> tournaments = tournamentRepo.findAll(pageable);
        List<TournamentView> tournamentViews = new ArrayList<>();
        tournaments.forEach(tournament -> {
            TournamentView tournamentView = tournamentToTournamentViewConverter.convert(tournament);
            tournamentViews.add(tournamentView);
        });
        return new PageImpl<>(tournamentViews, pageable, tournaments.getTotalElements());
    }

    public TournamentView create(TournamentBaseReq req) 
    {
        Tournament tournament = new Tournament();
        this.prepare(tournament,req);
        Tournament tournamentSave = tournamentRepo.save(tournament);
        return tournamentToTournamentViewConverter.convert(tournamentSave);
    }

    @Transactional
    public void delete(Long id)
    {
        try {
            tournamentRepo.deleteById(id);
        } 
        catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("tournament.NotFound", id));
        }
    }

    public TournamentView update(Tournament tournament, TournamentBaseReq req)
    {
        Tournament newTournament = this.prepare(tournament,req);
        Tournament tournamentSave = tournamentRepo.save(newTournament);
        return tournamentToTournamentViewConverter.convert(tournamentSave);
    }

    public Tournament prepare(Tournament tournament, TournamentBaseReq tournamentBaseReq)
    {
        tournament.setName(tournamentBaseReq.getName());
        tournament.setCountry(tournamentBaseReq.getCountry());
        List<Team> teamList = teamRepo.findAllById(tournamentBaseReq.getTeams()
                .stream()
                .map(BaseRequest.Id::getId)
                .collect(Collectors.toSet()));
        Set<Team> teams = new HashSet<>(teamList);
        tournament.setTeams(teams);
        return tournament;
    }
}
