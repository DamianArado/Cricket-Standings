INSERT INTO cc_tournament ("name", country)
VALUES
('Ball','Russia'),
('Foot','UK');

INSERT INTO cc_team ("name", players)
VALUES
('First', 43),
('Second', 50);

INSERT INTO cc_tournament_team (id_tournament, id_team)
VALUES
(1,1),
(1,2),
(2,1);

INSERT INTO cc_match (id_owners, id_guests, score_owners, score_guests, id_tournament, match_date)
VALUES
(1,2,5,0,1,'1999-01-08'),
(2,1,0,5,1,'1999-01-09'),
(2,1,2,3,2,'2020-01-01');

INSERT INTO cc_coach (id, id_team, surname, "name", age, experience)
VALUES
(1,1,'FirstCoach','Name',55,20),
(2,2,'SecondCoach','NameMore',64,30);

INSERT INTO cc_news (id, "name", content)
VALUES
(1,'FirstNews', 'HitHitHit'),
(2,'SecondNews', 'MissedMISS');

INSERT INTO cc_team_news (id_news, id_team)
VALUES
(1,2),
(1,1),
(2,1);

INSERT INTO cc_player (surname, "name", height, weight, age)
VALUES
('Robov', 'Vitaliy', 190, 85, 25),
('Vovov', 'Grey', 176, 67, 20),
('Иванов', 'Иван', 176, 67, 20);

INSERT INTO cc_player_team (id_player, id_team)
VALUES
(1,1),
(1,2),
(2,1),
(3,2);

INSERT INTO cc_event ("name", content)
VALUES
('FirstEvent', 'HitHitHit'),
('SecondEvent', 'MissedMISS');

INSERT INTO cc_player_event (id_event, id_player)
VALUES
(1,2),
(2,2),
(1,3);

INSERT INTO cc_tournament_result (id_team, id_tournament, place, points, wins,
                             loses, draw, goals_missed, goals, missed)
VALUES
(1,1,1,9,3,0,0,15,18,3),
(2,1,2,4,1,0,0,-15,3,18),
(2,2,2,4,1,0,0,-15,3,18);