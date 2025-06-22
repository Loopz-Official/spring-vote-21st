INSERT INTO candidate (candidate_id, created_at, updated_at, type) VALUES
  (1, now(), now(), 'PART_LEADER'),
  (2, now(), now(), 'PART_LEADER'),
  (3, now(), now(), 'PART_LEADER'),
  (4, now(), now(), 'PART_LEADER'),
  (5, now(), now(), 'PART_LEADER'),
  (6, now(), now(), 'PART_LEADER'),
  (7, now(), now(), 'PART_LEADER'),
  (8, now(), now(), 'PART_LEADER'),
  (9, now(), now(), 'PART_LEADER'),
  (10, now(), now(), 'PART_LEADER'),
    (11, now(), now(), 'PART_LEADER'),
    (12, now(), now(), 'PART_LEADER'),
    (13, now(), now(), 'PART_LEADER'),
    (14, now(), now(), 'PART_LEADER'),
    (15, now(), now(), 'PART_LEADER'),
    (16, now(), now(), 'PART_LEADER'),
    (17, now(), now(), 'PART_LEADER'),
    (18, now(), now(), 'PART_LEADER'),
    (19, now(), now(), 'PART_LEADER'),
    (20, now(), now(), 'PART_LEADER'),
    (21, now(), now(), 'DEMODAY'),
    (22, now(), now(), 'DEMODAY'),
    (23, now(), now(), 'DEMODAY'),
    (24, now(), now(), 'DEMODAY'),
    (25, now(), now(), 'DEMODAY')
    ON CONFLICT (candidate_id) DO NOTHING;

INSERT INTO PART_LEADER (part, candidate_id, name,team) VALUES
('BACKEND', 1, '김준형','LOOPZ'),
('BACKEND', 2, '임도현','LOOPZ'),
('BACKEND', 3, '박정하','PROMESA'),
('BACKEND', 4, '서채연','PROMESA'),
('BACKEND', 5, '이석원','HANIHOME'),
('BACKEND', 6, '최근호','HANIHOME'),
('BACKEND', 7, '오지현','DEARDREAM'),
('BACKEND', 8, '한혜수','DEARDREAM'),
('BACKEND', 9, '박서연','INFLUY'),
('BACKEND', 10, '박채연','INFLUY'),
('FRONTEND', 11, '김철흥','LOOPZ'),
('FRONTEND', 12, '송아영','LOOPZ'),
('FRONTEND', 13, '권동욱','PROMESA'),
('FRONTEND', 14, '김서연','PROMESA'),
('FRONTEND', 15, '신수진','HANIHOME'),
('FRONTEND', 16, '원채영','HANIHOME'),
('FRONTEND', 17, '김영서','DEARDREAM'),
('FRONTEND', 18, '이주희','DEARDREAM'),
('FRONTEND', 19, '최서연','INFLUY'),
('FRONTEND', 20, '한서정','INFLUY')
    ON CONFLICT (candidate_id) DO UPDATE SET
    team = EXCLUDED.team;


INSERT INTO demoday (candidate_id, team) VALUES
(21, 'LOOPZ'),
(22, 'HANIHOME'),
(23, 'DEARDREAM'),
(24, 'INFLUY'),
(25, 'PROMESA')
ON CONFLICT (candidate_id) DO NOTHING;
