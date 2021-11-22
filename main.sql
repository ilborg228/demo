create table bank (
   id CHAR(32) not null,
    name varchar(255),
    primary key (id)
);

create table calendar (
   id CHAR(32) not null,
    body_amount double not null,
    date date,
    payment_amount double not null,
    percent_amount double not null,
    offer_id CHAR(32),
    primary key (id)
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

create table offer (
   id CHAR(32) not null,
    loan_amount double,
    client_id CHAR(32),
    credit_id CHAR(32),
    primary key (id)
);

alter table calendar
   add constraint FKfyml1kv6010orctvfkxy25yhm
   foreign key (offer_id)
   references offer;

alter table client
   add constraint FKim95abd01ot21q2dn9mpxo7nc
   foreign key (bank_id)
   references bank;

alter table credit
   add constraint FK1x1iaf0dsp6s1tjhdihhedgdk
   foreign key (bank_id)
   references bank;

alter table offer
   add constraint FKqh66wuqh9ub2oqtulwtfnhi1n
   foreign key (client_id)
   references client;

alter table offer
   add constraint FKlrbdwf6atjx5kf10h12igmxrj
   foreign key (credit_id)
   references credit;