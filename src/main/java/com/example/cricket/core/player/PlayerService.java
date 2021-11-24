package com.example.cricket.core.player;

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
import com.example.cricket.core.player.converter.PlayerToPlayerViewConverter;
import com.example.cricket.core.player.web.PlayerView;
import com.example.cricket.core.player.web.PlayerBaseReq;
import com.example.cricket.core.team.Team;
import com.example.cricket.core.team.TeamRepo;
import com.example.cricket.error.EntityNotFoundException;
import com.example.cricket.util.MessageUtil;

@Service
public class PlayerService 
{

    private final PlayerRepo playerRepo;
    private final PlayerToPlayerViewConverter playerToPlayerViewConverter;
    private final TeamRepo teamRepo;
    private final MessageUtil messageUtil;

    public PlayerService(PlayerRepo playerRepo,
                         PlayerToPlayerViewConverter playerToPlayerViewConverter,
                         TeamRepo teamRepo,
                         MessageUtil messageUtil) 
    {
        this.playerRepo = playerRepo;
        this.playerToPlayerViewConverter = playerToPlayerViewConverter;
        this.teamRepo = teamRepo;
        this.messageUtil = messageUtil;
    }

    public Player findPlayerOrThrow(Long id) 
    {
        return playerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("player.NotFound", id)));
    }

    public PlayerView getPlayer(Long id) 
    {
        Player player = findPlayerOrThrow(id);
        return playerToPlayerViewConverter.convert(player);
    }

    public Page<PlayerView> findAllPlayer(Pageable pageable) 
    {
        Page<Player> players = playerRepo.findAll(pageable);
        List<PlayerView> playerViews = new ArrayList<>();
        players.forEach(player -> {
            PlayerView playerView = playerToPlayerViewConverter.convert(player);
            playerViews.add(playerView);
        });
        return new PageImpl<>(playerViews, pageable, players.getTotalElements());
    }

    public PlayerView create(PlayerBaseReq req) 
    {
        Player player = new Player();
        this.prepare(player, req);
        Player playerSave = playerRepo.save(player);
        return playerToPlayerViewConverter.convert(playerSave);
    }

    @Transactional
    public void delete(Long id) 
    {
        try {
            playerRepo.deleteById(id);
        } 
        catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("player.NotFound", id));
        }
    }

    public PlayerView update(Player player, PlayerBaseReq req) 
    {
        Player newPlayer = this.prepare(player,req);
        Player playerSave = playerRepo.save(newPlayer);
        return playerToPlayerViewConverter.convert(playerSave);
    }

    public Player prepare(Player player, PlayerBaseReq playerBaseReq)
    {
        player.setName(playerBaseReq.getName());
        player.setSurname(playerBaseReq.getSurname());
        player.setWeight(playerBaseReq.getWeight());
        player.setHeight(playerBaseReq.getHeight());
        player.setAge(playerBaseReq.getAge());
        List<Team> teamList = teamRepo.findAllById(playerBaseReq.getTeams()
                .stream()
                .map(BaseRequest.Id::getId)
                .collect(Collectors.toSet()));
        Set<Team> teams = new HashSet<>(teamList);
        player.setTeams(teams);
        return player;
    }
}
