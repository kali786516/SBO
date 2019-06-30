# --- First database schema

# --- !Ups

create table user (
  email                     varchar(255) not null,
  password                  varchar(255) not null
);

