package com.example.cricket.core.match;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.example.cricket.core.team.Team;
import com.example.cricket.core.tournament.Tournament;

@Entity
@Table(name = "cc_map")
public class Match 
{

    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "cc_map_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = 
            {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "cc_map_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cc_map_id_seq")
    private long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_owners")
    private Team teamOwner;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_guests")
    private Team teamGuest;

    @Column(name = "score_owners")
    private int scoreOwners;

    @Column(name = "score_guests")
    private int scoreGuests;

    @Column(name = "match_date")
    private Date matchDate;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_tournament")
    private Tournament tournament;

    public long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Team getOwner()
    {
        return teamOwner;
    }

    public void setOwner(Team teamOwner)
    {
        this.teamOwner = teamOwner;
    }

    public Team getGuest()
    {
        return teamGuest;
    }

    public void setGuest(Team teamGuest)
    {
        this.teamGuest = teamGuest;
    }

    public int getScoreOwners()
    {
        return scoreOwners;
    }

    public void setScoreOwners(int scoreOwners)
    {
        this.scoreOwners = scoreOwners;
    }

    public int getScoreGuests()
    {
        return scoreGuests;
    }

    public void setScoreGuests(int scoreGuests)
    {
        this.scoreGuests = scoreGuests;
    }

    public Date getMatchDate()
    {
        return matchDate;
    }

    public void setMatchDate(Date matchDate)
    {
        this.matchDate = matchDate;
    }

    public Tournament getTournament()
    {
        return tournament;
    }

    public void setTournament(Tournament tournament)
    {
        this.tournament = tournament;
    }
}
