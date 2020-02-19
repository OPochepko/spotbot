create table spot_messages
(
    id serial not null unique ,
    location varchar(50) not null unique ,
    message varchar(200) not null
);

