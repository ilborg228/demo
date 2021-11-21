create table bank (
    id bigint not null,
    name varchar(255),
    primary key (id)
);

create table bank_clients (
   banks_id bigint not null,
    clients_id bigint not null
);

create table bank_credits (
   bank_id bigint not null,
    credits_id bigint not null
);

create table calendar (
   id bigint not null,
    body_amount double not null,
    date date,
    payment_amount double not null,
    percent_amount double not null,
    offer_id bigint,
    primary key (id)
);

create table client (
   id bigint not null,
    email varchar(255),
    name varchar(255),
    passport varchar(255),
    phone varchar(255),
    primary key (id)
);

create table credit (
   id bigint not null,
    limit double not null,
    percent double not null,
    offer_id bigint,
    primary key (id)
);

create table offer (
   id bigint not null,
    loan_amount bigint,
    client_id bigint,
    credit_id bigint,
    primary key (id)
);

alter table bank_clients
   add constraint FKo9rsclnq8ii0x061nl44lw1lv
   foreign key (clients_id)
   references client;

alter table bank_clients
   add constraint FK3v2qf3g39kc8hctpbox4vw12s
   foreign key (banks_id)
   references bank;

alter table bank_credits
   add constraint FKrg0dql4nr0f6k4g33ttjb74q7
   foreign key (credits_id)
   references credit;

alter table bank_credits
   add constraint FK7abey5qoaa04bk7gk7rv2whge
   foreign key (bank_id)
   references bank;

alter table calendar
   add constraint FKfyml1kv6010orctvfkxy25yhm
   foreign key (offer_id)
   references offer;

alter table credit
   add constraint FK57auxbdf41ywoo4g2d73yol79
   foreign key (offer_id)
   references offer;


alter table offer
   add constraint FKqh66wuqh9ub2oqtulwtfnhi1n
   foreign key (client_id)
   references client;

alter table offer
   add constraint FKlrbdwf6atjx5kf10h12igmxrj
   foreign key (credit_id)
   references credit;