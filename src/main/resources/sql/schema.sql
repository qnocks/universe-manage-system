CREATE TABLE ruler (
    id int PRIMARY KEY,
    name varchar(50) NOT NULL,
    age int
);

CREATE TABLE planet (
    id int PRIMARY KEY,
    name varchar(50) NOT NULL,
    ruler_id int,
    FOREIGN KEY (ruler_id) REFERENCES ruler(id)
);

CREATE TABLE user (
    id int PRIMARY KEY,
    username varchar(100),
    password varchar(255),
    status varchar(25)
);

CREATE TABLE role (
    id int PRIMARY KEY,
    name varchar(25)
);

CREATE TABLE user_role (
    user_id int,
    role_id int,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (role_id) REFERENCES role(id),
    CONSTRAINT user_role_id PRIMARY KEY (user_id, role_id)
);
