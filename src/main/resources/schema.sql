DROP TABLE IF EXISTS VOTES;
DROP TABLE IF EXISTS DISHES;
DROP TABLE IF EXISTS RESTAURANTS;
DROP TABLE IF EXISTS USER_ROLES ;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS ADMINS;


CREATE TABLE USERS
(
    id       INTEGER AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled  boolean DEFAULT TRUE  NOT NULL
);

CREATE TABLE USER_ROLES
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_inx UNIQUE(user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS(id)ON DELETE CASCADE
);

CREATE UNIQUE INDEX users_unique_email_idx
    ON USERS (email);


CREATE TABLE RESTAURANTS
(
    id   INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX restaurants_unique_name
    ON RESTAURANTS (name);

CREATE TABLE DISHES
(
    id            INTEGER AUTO_INCREMENT PRIMARY KEY,
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
    id                  INTEGER AUTO_INCREMENT PRIMARY KEY,
    date                DATE         NOT NULL,
    time                TIME         NOT NULL,
    restaurant_id       INTEGER      NOT NULL,
    restaurant_name     VARCHAR(256) NOT NULL,
    user_id             INTEGER      NOT NULL,
    user_email          VARCHAR(256) NOT NULL
);
CREATE UNIQUE INDEX vote_unique_user_date
    ON VOTES (date,user_id);
