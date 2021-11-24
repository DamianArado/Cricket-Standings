package com.example.cricket.core.player.web;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.cricket.core.player.Player;
import com.example.cricket.core.player.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController 
{

    private final PlayerService service;

    public PlayerController(final PlayerService service) 
    {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public PlayerView getPlayer(@PathVariable Long id) 
    {
        return service.getPlayer(id);
    }

    @GetMapping
    @ResponseBody
    public Page<PlayerView> getAllPlayer(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) 
        Pageable pageable) 
    {
        return service.findAllPlayer(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PlayerView create(@RequestBody @Valid PlayerBaseReq req) 
    {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlayer(@PathVariable Long id)
    {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public PlayerView updatePlayer(@PathVariable(name = "id") Long id,
                                @RequestBody @Valid PlayerBaseReq req)
    {
        Player player = service.findPlayerOrThrow(id);
        return service.update(player, req);
    }
}
