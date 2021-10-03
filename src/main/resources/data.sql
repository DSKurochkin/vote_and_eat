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
       ('User2', 'user2@vote.com', '$2a$12$sydiGyh.FIiQi5bcGh3ElOC6W849SF7Pi/d6keEvegZvS1aiYiiAa', true),
       ('User3', 'user3@vote.com', '$2a$12$J317cm3lG.Kw1gMnmDUM1.SHNf8JliJAEOA.mhKomAOznrY2Tv4xm', true);
-- pass--
-- admin
-- user1
-- user2
-- user4

INSERT INTO USER_ROLES(role, user_id)
values ('ADMIN', 1000),
       ('USER', 1001),
       ('USER', 1002),
       ('USER', 1003);

INSERT INTO RESTAURANTS(name)
VALUES ('RestaurantA'),
       ('RestaurantB');

INSERT INTO DISHES(name, date, price, restaurant_id)
VALUES ('A_dish first', '2021-09-01', 80, 1004),
       ('A_dish second', '2021-09-01', 70, 1004),
       ('A_kompot', '2021-09-01', 5, 1004),

       ('B_dish first', '2021-09-01', 60, 1005),
       ('B_dish second', '2021-09-01', 50, 1005),
       ('B_ice_cream', '2021-09-01', 10, 1005),

       ('A_2nd Sep', '2021-09-02', 98, 1004),
       ('B_2nd Sep', '2021-09-02', 99, 1005);


INSERT INTO VOTES(date, time, RESTAURANT_ID, RESTAURANT_NAME, USER_ID, USER_EMAIL)
VALUES ('2021-09-01', '09:00:00', 1004, 'RestaurantA', 1001, 'user1@vote.com'),
       ('2021-09-01', '08:00:00', 1005, 'RestaurantB', 1002, 'user2@vote.com'),
       ('2021-09-01', '07:59:00', 1004, 'RestaurantA', 1003, 'user3@vote.com'),

       ('2021-09-02', '07:30:00', 1005, 'RestaurantB', 1001, 'user1@vote.com'),
       ('2021-09-02', '08:30:00', 1004, 'RestaurantA', 1002, 'user2@vote.com'),
       ('2021-09-02', '08:20:00', 1004, 'RestaurantA', 1003, 'user3@vote.com');



