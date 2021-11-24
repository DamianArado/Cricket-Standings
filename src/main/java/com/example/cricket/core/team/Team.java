package com.example.cricket.core.team;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "cc_team")
public class Team 
{

    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "cc_team_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = 
            {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "cc_team_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cc_team_id_seq")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "players")
    private int numPlayers;


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

    public int getNumPlayers() 
    {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) 
    {
        this.numPlayers = numPlayers;
    }
}
