
CREATE TABLE users
(
    user_id  INTEGER  AUTO_INCREMENT,
    login    varchar(255)  NOT NULL,
    password varchar(255)  NOT NULL,
    role     varchar(10)  NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE orders
(
    order_id integer  AUTO_INCREMENT,
    user_id  integer  NOT NULL ,
    car_id   integer  NOT NULL,
    price decimal  NOT NULL,
    creation_date_order DATE  NOT NULL,
    PRIMARY KEY(order_id),
    FOREIGN KEY (user_id) REFERENCES USERS (user_id)
);