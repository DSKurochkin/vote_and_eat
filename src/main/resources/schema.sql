DROP TABLE IF EXISTS VOTES;
DROP TABLE IF EXISTS DISHES;
DROP TABLE IF EXISTS RESTAURANTS;
DROP TABLE IF EXISTS USERS;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE GLOBAL_SEQ START WITH 10000
    INCREMENT BY 1;

CREATE TABLE USERS
(
    id       INTEGER GENERATED BY DEFAULT AS IDENTITY SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    isAdmin  boolean DEFAULT false
);
CREATE UNIQUE INDEX users_unique_email_idx
    ON USERS (email);

CREATE TABLE RESTAURANTS
(
    id   INTEGER GENERATED BY DEFAULT AS IDENTITY SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES USERS (id)
);

CREATE TABLE DISHES
(
    id            INTEGER GENERATED BY DEFAULT AS IDENTITY SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    date          DATE         NOT NULL,
    price         INTEGER      NOT NULL,
    restaurant_id INTEGER      NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES RESTAURANTS (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX dishes_unique_date_name
    ON DISHES (date, name);

CREATE TABLE VOTES
(
    id            INTEGER GENERATED BY DEFAULT AS IDENTITY SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    date          DATE         NOT NULL,
    time          TIME,
    restaurant_id INTEGER      NOT NULL,
    user_id       INTEGER      NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES RESTAURANTS (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX vote_unique_user_date
    ON VOTES (date,user_id);

