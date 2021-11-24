package com.example.cricket.core.match;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatchRepo extends JpaRepository<Match, Long> 
{

    @Query(value = "SELECT m.id, m.id_guests, m.id_owners, m.id_tournament, match_date, te.command_name, ta.command_name,\n" +
            "score_owners, score_guests FROM cc_match m\n" +
            "JOIN cc_team te ON (m.id_owners = te.id)\n" +
            "JOIN cc_player_team pt ON (te.id = pt.id_team)\n" +
            "JOIN cc_player p ON (pt.id_player = p.id)\n" +
            "JOIN cc_team ta ON (m.id_guests = ta.id)\n" +
            "JOIN cc_player_team pl ON (ta.id = pl.id_team)\n" +
            "JOIN cc_player py ON (pl.id_player = py.id)\n" +
            "WHERE (id_tournament = (SELECT id_tournament FROM cc_match WHERE match_date = (SELECT max(match_date) FROM cc_match)))\n" +
            "AND ((py.surname_player = :playerSurname AND py.name_player = :playerName) \n" +
            "OR (p.surname_player = :playerSurname AND p.name_player = :playerName))\n" +
            "GROUP BY m.id, m.id_guests, m.id_owners, m.id_tournament, score_owners, score_guests, match_date, te.command_name, ta.command_name", nativeQuery = true)
    List<Match> getLastMatch(@Param("playerSurname") String playerSurname, @Param("playerName") String playerName);
}
