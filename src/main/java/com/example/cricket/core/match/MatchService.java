package com.example.cricket.core.match;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.cricket.core.match.converter.MatchToMatchViewConverter;
import com.example.cricket.core.match.web.MatchView;
import com.example.cricket.core.match.web.MatchBaseReq;
import com.example.cricket.core.match.web.MatchLastReq;
import com.example.cricket.core.team.TeamRepo;
import com.example.cricket.core.tournament.TournamentRepo;
import com.example.cricket.error.EntityNotFoundException;
import com.example.cricket.util.MessageUtil;

@Service
public class MatchService 
{

    private final MatchRepo matchRepo;
    private final MatchToMatchViewConverter matchToMatchViewConverter;
    private final TeamRepo teamRepo;
    private final TournamentRepo tournamentRepo;
    private final MessageUtil messageUtil;

    public MatchService(MatchRepo matchRepo,
                        MatchToMatchViewConverter matchToMatchViewConverter,
                        TeamRepo teamRepo,
                        TournamentRepo tournamentRepo,
                        MessageUtil messageUtil) 
    {
        this.matchRepo = matchRepo;
        this.matchToMatchViewConverter = matchToMatchViewConverter;
        this.teamRepo = teamRepo;
        this.tournamentRepo = tournamentRepo;
        this.messageUtil = messageUtil;
    }

    public List<MatchView> findLastMatch(MatchLastReq req) 
    {
        List<Match> matches = matchRepo.getLastMatch(req.getPlayerSurname(), req.getPlayerName());
        List<MatchView> matchViews = new ArrayList<>();
        matches.forEach(match -> matchViews.add(matchToMatchViewConverter.convert(match)));
        return matchViews;
    }

    public Match findMatchOrThrow(Long id) 
    {
        return matchRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("match.NotFound", id)));
    }

    public MatchView getMatch(Long id) 
    {
        Match match = findMatchOrThrow(id);
        return matchToMatchViewConverter.convert(match);
    }

    public Page<MatchView> findAllMatch(Pageable pageable) 
    {
        Page<Match> matches = matchRepo.findAll(pageable);
        List<MatchView> matchViews = new ArrayList<>();
        matches.forEach(match -> {
            MatchView matchView = matchToMatchViewConverter.convert(match);
            matchViews.add(matchView);
        });
        return new PageImpl<>(matchViews, pageable, matches.getTotalElements());
    }

    public MatchView create(MatchBaseReq req) 
    {
        Match match = new Match();
        this.prepare(match,req);
        Match matchSave = matchRepo.save(match);
        return matchToMatchViewConverter.convert(matchSave);
    }

    public void delete(Long id) 
    {
        try {
            matchRepo.deleteById(id);
        } 
        catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("match.NotFound", id));
        }
    }

    public MatchView update(Match match, MatchBaseReq req)
    {
        Match newMatch = this.prepare(match,req);
        Match matchSave = matchRepo.save(newMatch);
        return matchToMatchViewConverter.convert(matchSave);
    }

    private Match prepare(Match match, MatchBaseReq req) 
    {
        match.setMatchDate(req.getMatchDate());
        match.setScoreOwners(req.getScoreOwners());
        match.setScoreGuests(req.getScoreGuests());
        match.setTournament(tournamentRepo.getOne(req.getTournamentId()));
        match.setOwner(teamRepo.getOne(req.getTeamOwnerId()));
        match.setGuest(teamRepo.getOne(req.getTeamGuestId()));
        return match;
    }
}
