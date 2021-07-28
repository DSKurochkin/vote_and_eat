DELETE FROM DISHES;
DELETE FROM RESTAURANTS;
DELETE FROM USERS;
DELETE FROM VOTES;
ALTER SEQUENCE global_seq RESTART WITH 10000;

INSERT INTO USERS (name, email, password, isadmin)
VALUES ('admin1', 'admin1@vote.com', 'admin1', true),
       ('admin2', 'admin2@vote.com', 'admin2', true),
       ('Angus', 'angus@vote.com', 'angus', false),
       ('David ', 'david@vote.com', 'david', false),
       ('user', 'user@vote.com', 'user', false),
       ('Janis', 'janis@vote.com', 'janis', false);

INSERT INTO RESTAURANTS(name)
VALUES ('Kurskiy'),
       ('Tsarskiy'),
       ('Krusty Krab');

INSERT INTO DISHES(name, date, price, restaurant_id)
VALUES ('Befstroganov', '2021-07-16', 1000, 1),
       ('Heres', '2021-07-19', 800, 1),
       ('Vimya', '2021-07-16', 1000, 1),

       ('Ikra chernaya', '2021-07-19', 1000, 2),
       ('Ikra krasnaya', '2021-07-16', 1000, 2),
       ('Ikra baklazhannaya', '2021-07-16', 1000, 2),

       ('Krabby Patty 1', '2021-07-16', 900, 3),
       ('Krabby Patty 2', '2021-07-16', 800, 3),
       ('Krabby Patty 3', '2021-07-16', 1100, 3);

INSERT INTO VOTES(time, date, user_id, restaurant_id)
VALUES ( '09:00:00' , '2021-07-16', 1, 1 ),
       ( '08:00:00' , '2021-07-16', 2, 2 ),
       ( '07:00:00' , '2021-07-16', 3, 3 ),
       ( '12:00:00' , '2021-07-16', 4, 1 );


