package com.example.cricket.core.event.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.example.cricket.core.event.Event;
import com.example.cricket.core.event.web.EventView;
import com.example.cricket.core.player.Player;
import com.example.cricket.core.player.converter.PlayerToPlayerViewConverter;
import com.example.cricket.core.player.web.PlayerView;

@Component
public class EventToEventViewConverter implements Converter<Event, EventView> 
{

    private final PlayerToPlayerViewConverter playerToPlayerViewConverter;

    public EventToEventViewConverter(PlayerToPlayerViewConverter playerToPlayerViewConverter) 
    {
        this.playerToPlayerViewConverter = playerToPlayerViewConverter;
    }

    @Override
    public EventView convert(@NonNull Event event) 
    {
        EventView view = new EventView();
        view.setId(event.getId());
        view.setName(event.getName());
        view.setContent(event.getContent());
        Set<PlayerView> views = new HashSet<>();
        Set<Player> players= event.getPlayers();
        players.forEach(player -> views.add(playerToPlayerViewConverter.convert(player)));
        view.setPlayers(views);
        return view;
    }
}
