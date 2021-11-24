package com.example.cricket.core.news;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.example.cricket.core.team.Team;

@Entity
@Table(name = "cc_news")
public class News 
{

    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "cc_news_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = 
            {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "cc_news_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cc_news_id_seq")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "fc_team_news",
            joinColumns = { @JoinColumn(name = "id_news") },
            inverseJoinColumns = { @JoinColumn(name = "id_team") })
    private Set<Team> teams = new HashSet<>();


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

    public Set<Team> getTeams() 
    {
        return teams;
    }

    public void setTeams(Set<Team> teams) 
    {
        this.teams = teams;
    }
}
