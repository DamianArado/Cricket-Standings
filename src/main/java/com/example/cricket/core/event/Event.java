package com.example.cricket.core.event;

import java.util.Set;

import javax.persistence.*;
import java.util.HashSet;

import org.hibernate.annotations.GenericGenerator;

import com.example.cricket.core.player.Player;

@Entity
@Table(name = "cc_event")
public class Event 
{

    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "cc_event_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = 
            {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "cc_event_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cc_event_id_seq")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "fc_player_event",
            joinColumns = { @JoinColumn(name = "id_event") },
            inverseJoinColumns = { @JoinColumn(name = "id_player") })
    private Set<Player> players = new HashSet<>();


    public long getId() 
    {
        return id;
    }

    public void setId(long id) 
    {
        this.id = id;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getContent() 
    {
        return content;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public Set<Player> getPlayers() 
    {
        return players;
    }

    public void setPlayers(Set<Player> players) 
    {
        this.players = players;
    }
}
