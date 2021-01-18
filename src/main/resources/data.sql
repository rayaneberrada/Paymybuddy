INSERT INTO user (first_name, last_name, email, money, activated)
VALUES ('rayane', 'berrada', 'be@gmail.cm', 1000, 1),
       ('daniel', 'radcliff', 'danielradcliff@gmail.cm', 746, 1),
       ('hervey', 'heipstein', 'herveyheipstein@gmail.cm', 100000000, 0),
       ('john', 'boyd', 'johnboyd@gmail.cm', 500, 1);

INSERT INTO beneficiary (user_sending_id, user_receiving_id)
VALUES (1, 2),
       (1, 3),
       (1, 4),
       (2, 3),
       (2, 4),
       (4, 1);