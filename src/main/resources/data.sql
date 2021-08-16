DELETE FROM DISHES;
DELETE FROM RESTAURANTS;
DELETE FROM USERS;
DELETE FROM VOTES;

INSERT INTO USERS (name, email, password, admin)
VALUES ('Admin', 'admin@vote.com', 'admin@vote.com', true),
       ('User1', 'user1@vote.com', 'user1@vote.com', false),
       ('User2 ', 'user2@vote.com', 'user2@vote.com', false);

INSERT INTO RESTAURANTS(name)
VALUES ('Restaurant A'),
       ('Restaurant B');

INSERT INTO DISHES(name, date, price, restaurant_id)
VALUES ('A_dish first', '2021-07-16', 80, 1),
       ('A_dish second', '2021-07-17', 70, 1),
       ('A_kompot', '2021-07-16', 5, 1),

       ('B_dish first', '2021-07-17', 60, 2),
       ('B_dish second', '2021-07-16', 50, 2),
       ('B_ice_cream', '2021-07-16', 10, 2);

INSERT INTO VOTES(time, date, user_id, restaurant_id)
VALUES ( '09:00:00' , '2021-07-16', 2, 1 ),
       ( '08:00:00' , '2021-07-16', 3, 2 );



