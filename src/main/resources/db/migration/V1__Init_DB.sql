
CREATE TABLE users
(
    id  INTEGER  AUTO_INCREMENT,
    login    varchar(50)  NOT NULL,
    password varchar(255)  NOT NULL,
    role     varchar(10)  NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id integer  AUTO_INCREMENT,
    user_id  integer  NOT NULL ,
    car_id   integer  NOT NULL,
    price decimal  NOT NULL,
    creation_date_order DATE  NOT NULL,
    canceled_order boolean default false NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES USERS (id)
);