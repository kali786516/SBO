# --- First database schema

# --- !Ups

create table MyUser (
  email                     varchar(255) not null,
  password                  varchar(255) not null
);

