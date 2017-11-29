-- LEVELS INSERTION

INSERT INTO levels (level, xp)
VALUES (1, 1000);
INSERT INTO levels (level, xp)
VALUES (2, 1000);
INSERT INTO levels (level, xp)
VALUES (3, 1000);
INSERT INTO levels (level, xp)
VALUES (4, 1000);
INSERT INTO levels (level, xp)
VALUES (5, 1000);
INSERT INTO levels (level, xp)
VALUES (6, 1000);
INSERT INTO levels (level, xp)
VALUES (7, 1000);
INSERT INTO levels (level, xp)
VALUES (8, 1000);
INSERT INTO levels (level, xp)
VALUES (9, 1000);
INSERT INTO levels (level, xp)
VALUES (10, 1000);


-- SKILLS INSERTION

INSERT INTO skills (id, name, type, prerequisites)
VALUES (0, 'Programming', 'BASIC', '');
INSERT INTO skills (id, name, type, prerequisites)
VALUES (1, 'Java', 'BASIC', '0');
INSERT INTO skills (id, name, type, prerequisites)
VALUES (2, 'Python3', 'ADAPTIVE', '0');
INSERT INTO skills (id, name, type, prerequisites)
VALUES (3, 'C', 'BASIC', '0');
INSERT INTO skills (id, name, type, prerequisites)
VALUES (4, 'Shell scripting', 'ADAPTIVE', '');
INSERT INTO skills (id, name, type, prerequisites)
VALUES (5, 'Databases', 'BASIC', '');
INSERT INTO skills (id, name, type, prerequisites)
VALUES (6, 'SQL', 'ADAPTIVE', '5');
INSERT INTO skills (id, name, type, prerequisites)
VALUES (7, 'Mongo', 'ADAPTIVE', '5');
INSERT INTO skills (id, name, type, prerequisites)
VALUES (8, 'Spring', 'BASIC', '1,6');
INSERT INTO skills (id, name, type, prerequisites)
VALUES (9, 'FireWall', 'DEFENSIVE', '0');
INSERT INTO skills (id, name, type, prerequisites)
VALUES (10, 'Javascript', 'OFFENSIVE', '0');
INSERT INTO skills (id, name, type, prerequisites)
VALUES (11, 'Brainfuck', 'OFFENSIVE', '1,2,3,4,5');

-- JOBS INSERTION

INSERT INTO jobs (id, name, payment, xp, time, energy, prerequisites)
VALUES (0, 'Software development', 5, 150, 10, 5, '0');
INSERT INTO jobs (id, name, payment, xp, time, energy, prerequisites)
VALUES (1, 'Googling', 1, 10, 1, 0, '');
INSERT INTO jobs (id, name, payment, xp, time, energy, prerequisites)
VALUES (2, 'Web application development', 100, 500, 120, 40, '8');
INSERT INTO jobs (id, name, payment, xp, time, energy, prerequisites)
VALUES (3, 'Database administration', 20, 260, 60, 23, '5');
INSERT INTO jobs (id, name, payment, xp, time, energy, prerequisites)
VALUES (4, 'Tensorflow', 30, 300, 69, 30, '2');
INSERT INTO jobs (id, name, payment, xp, time, energy, prerequisites)
VALUES (5, 'Linux kernel development', 0, 250, 5, 20, '3,4');
INSERT INTO jobs (id, name, payment, xp, time, energy, prerequisites)
VALUES (6, 'UI development', 15, 250, 30, 15, '10');