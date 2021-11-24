package com.example.cricket.core.event;

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
import com.example.cricket.core.event.converter.EventToEventViewConverter;
import com.example.cricket.core.event.web.EventView;
import com.example.cricket.core.event.web.EventBaseReq;
import com.example.cricket.core.player.Player;
import com.example.cricket.core.player.PlayerRepo;
import com.example.cricket.error.EntityNotFoundException;
import com.example.cricket.util.MessageUtil;

@Service
public class EventService 
{

    private final EventRepo eventRepo;
    private final EventToEventViewConverter eventToEventViewConverter;
    private final PlayerRepo playerRepo;
    private final MessageUtil messageUtil;

    public EventService(EventRepo eventRepo,
                        EventToEventViewConverter eventToEventViewConverter,
                        PlayerRepo playerRepo,
                        MessageUtil messageUtil) 
    {
        this.eventRepo = eventRepo;
        this.eventToEventViewConverter = eventToEventViewConverter;
        this.playerRepo = playerRepo;
        this.messageUtil = messageUtil;
    }

    public Event findEventOrThrow(Long id) 
    {
        return eventRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("event.NotFound", id)));
    }

    public EventView getEvent(Long id) 
    {
        Event event = findEventOrThrow(id);
        return eventToEventViewConverter.convert(event);
    }

    public Page<EventView> findAllEvent(Pageable pageable) 
    {
        Page<Event> events = eventRepo.findAll(pageable);
        List<EventView> eventViews = new ArrayList<>();
        events.forEach(event -> {
            EventView eventView = eventToEventViewConverter.convert(event);
            eventViews.add(eventView);
        });
        return new PageImpl<>(eventViews, pageable, events.getTotalElements());
    }

    public EventView create(EventBaseReq req) 
    {
        Event event = new Event();
        this.prepare(event,req);
        Event eventSave = eventRepo.save(event);
        return eventToEventViewConverter.convert(eventSave);
    }

    @Transactional
    public void delete(Long id) 
    {
        try {
            eventRepo.deleteById(id);
        } 
        catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("event.NotFound", id));
        }
    }

    public EventView update(Event event, EventBaseReq req) 
    {
        Event newEvent = this.prepare(event,req);
        Event eventSave = eventRepo.save(newEvent);
        return eventToEventViewConverter.convert(eventSave);
    }

    private Event prepare(Event event, EventBaseReq req) 
    {
        event.setName(req.getName());
        event.setContent(req.getContent());
        List<Player> playerList = playerRepo.findAllById(req.getPlayers()
                .stream()
                .map(BaseRequest.Id::getId)
                .collect(Collectors.toSet()));
        Set<Player> players = new HashSet<>(playerList);
        event.setPlayers(players);
        return event;
    }
}
