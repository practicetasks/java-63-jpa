create table users
(
    id    serial primary key,
    name  varchar,
    email varchar,
    age   int
);

insert into users (name, email, age)
values ('testUser', 'test@gmail.com', 20);
insert into users (name, email, age)
values ('testUser2', 'test2@gmail.com', 22);


create table posts
(
    id         serial primary key,
    title      varchar(50),
    subtitle   varchar,
    created_at timestamp,
    user_id    integer references users (id)
);

set time zone 'Asia/Almaty';

insert into posts (title, subtitle, created_at, user_id)
values ('testPost2', '', now(), (select id from users where name = 'testUser'));



create table options
(
    id          serial primary key,
    name        varchar not null,
    category_id integer references categories (id)
);

create table values
(
    id         serial primary key,
    name       varchar not null,
    product_id integer references products (id),
    option_id  integer references options (id)
);