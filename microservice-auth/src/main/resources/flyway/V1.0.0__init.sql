create table user
(
    id       int primary key auto_increment,
    username varchar(8)   not null unique,
    password varchar(128) not null,
    email    varchar(32) unique
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

create table user_authority_relationship
(
    id           int primary key auto_increment,
    user_id      int not null,
    authority_id int not null
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

create table authority
(
    id          int primary key auto_increment,
    name        varchar(16) unique not null,
    create_time timestamp default now()
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;