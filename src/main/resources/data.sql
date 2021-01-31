INSERT INTO user (username, password, email, money, enabled, role)
VALUES ('rayane', 'abcd', 'be@gmail.cm', 1000, 1, 'USER'),
       ('daniel', '1234', 'danielradcliff@gmail.cm', 746, 1, 'USER'),
       ('hervey', '1234', 'herveyheipstein@gmail.cm', 100000000, 0, 'USER'),
       ('john', '1234', 'johnboyd@gmail.cm', 500, 1, 'USER');

INSERT INTO beneficiary (user_sending_id, user_receiving_id)
VALUES (1, 2),
       (1, 3),
       (1, 4),
       (2, 3),
       (2, 4),
       (4, 1);

INSERT INTO card (number, expiration_date, user_id)
VALUES ('1234546712340978', '2021-10-01', 1),
       ('8694387340384723', '2022-12-01', 2),
       ('8213434340384723', '2022-08-01', 3),
       ('4394387340332134', '2021-05-01', 2),
       ('0998387340384123', '2021-09-01', 4);

INSERT INTO transactions (user_receiving_id, amount, card_id, date)
VALUES (2, 300, 1, '2021-01-01'),
       (3, 760, 4, '2020-12-04'),
       (1, 575.5, 2, '2020-10-09'),
       (3, 124, 1, '2020-12-12'),
       (4, 20, 1, '2020-10-30'),
       (4, 10, 2, '2021-01-01');