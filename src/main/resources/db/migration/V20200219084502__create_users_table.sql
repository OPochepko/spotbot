create table users
(
    id       serial not null unique ,
    username    varchar(100) unique ,
    password varchar(100),
    role     varchar(10)
);