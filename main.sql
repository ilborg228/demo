drop table if exists calendar;
drop table if exists offer;
drop table if exists client;
drop table if exists credit;
drop table if exists bank;

create table calendar (
      id CHAR(32) not null,
      body_amount double not null,
      date date,
      payment_amount double not null,
      percent_amount double not null,
      offer_id CHAR(32),
      primary key (id)
);

create table offer (
   id CHAR(32) not null,
    name varchar(255),
    primary key (id),
   client_id CHAR(32),
   credit_id CHAR(32)
);

create table client (
   id CHAR(32) not null,
    email varchar(255),
    name varchar(255),
    passport varchar(255),
    phone varchar(255),
    bank_id CHAR(32),
    primary key (id)
);

create table credit (
   id CHAR(32) not null,
    limit double not null,
    percent double not null,
    bank_id CHAR(32),
    primary key (id)
);

create table bank (
      id CHAR(32) not null,
      name varchar(255),
      primary key (id)
);

alter table calendar
   add foreign key (offer_id)
   references offer;

alter table client
   add foreign key (bank_id)
   references bank;

alter table credit
   add foreign key (bank_id)
   references bank;

alter table offer
   add foreign key (client_id)
   references client;

alter table offer
   add foreign key (credit_id)
   references credit;