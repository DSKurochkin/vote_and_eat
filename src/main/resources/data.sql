DELETE
FROM DISHES;
DELETE
FROM RESTAURANTS;
DELETE
FROM USERS;
DELETE
FROM VOTES;

INSERT INTO USERS (name, email, password, enabled)
VALUES ('Admin', 'admin@vote.com', '$2a$12$3SylX11BsbC2icIbTsnYUuBxdiw2F.mGB5a9c.QR7gBMdvHPYoi.O', true),
       ('User1', 'user1@vote.com', '$2a$12$yFwzAoaFJ3jNUNbZAeG/ku.aa9w581whoIuNHf4o6c5NDxFrUuXqa', true),
       ('User2', 'user2@vote.com', '$2a$12$sydiGyh.FIiQi5bcGh3ElOC6W849SF7Pi/d6keEvegZvS1aiYiiAa', true);

INSERT INTO USER_ROLES(role, user_id)
values ('ADMIN', 1),
       ('USER', 2),
       ('USER', 3);

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
VALUES ('09:00:00', '2021-07-16', 2, 1),
       ('08:00:00', '2021-07-16', 3, 2);



