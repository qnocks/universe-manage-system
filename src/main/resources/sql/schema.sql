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
