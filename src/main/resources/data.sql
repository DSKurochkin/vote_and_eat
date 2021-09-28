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
values ('ADMIN', 1),
       ('USER', 2),
       ('USER', 3),
       ('USER', 4);

INSERT INTO RESTAURANTS(name)
VALUES ('RestaurantA'),
       ('RestaurantB');

INSERT INTO DISHES(name, date, price, restaurant_id)
VALUES ('A_dish first', '2021-09-01', 80, 1),
       ('A_dish second', '2021-09-01', 70, 1),
       ('A_kompot', '2021-09-01', 5, 1),

       ('B_dish first', '2021-09-01', 60, 2),
       ('B_dish second', '2021-09-01', 50, 2),
       ('B_ice_cream', '2021-09-01', 10, 2),

       ('A_2nd Sep', '2021-09-02', 98, 1),
       ('B_2nd Sep', '2021-09-02', 99, 2);


INSERT INTO VOTES(date, time, RESTAURANT_ID, RESTAURANT_NAME, USER_ID, USER_EMAIL)
VALUES ('2021-09-01', '09:00:00', 1, 'RestaurantA', 2, 'user1@vote.com'),
       ('2021-09-01', '08:00:00', 2,'RestaurantB', 3, 'user2@vote.com'),
       ('2021-09-01', '07:59:00', 1,'RestaurantA', 4, 'user3@vote.com'),

       ('2021-09-02', '07:30:00', 2,'RestaurantB',  2,'user1@vote.com'),
       ('2021-09-02', '08:30:00', 1,'RestaurantA', 3,'user2@vote.com'),
       ('2021-09-02', '08:20:00', 1,'RestaurantA', 4,'user3@vote.com');



