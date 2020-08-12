
drop table if exists book cascade;
create table book
(
    id          uuid                    not null
        constraint book_pk
            primary key,
    name        varchar(255)            not null,
    created     timestamp default now() not null,
    description varchar(255),
    available   boolean   default false not null
);

alter table book
    owner to postgres;

create unique index book_id_uindex
    on book (id);

create unique index book_name_uindex
    on book (name);



drop table if exists product cascade;
create table product
(
    id bigserial not null,
    name VARCHAR(255) not null,
    cost INTEGER not null
);

create unique index product_id_uindex
    on product (id);

create unique index product_name_uindex
    on product (name);

alter table product
    add constraint product_pk
        primary key (id);


