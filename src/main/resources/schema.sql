create table users
(
    id    serial primary key,
    naame  varchar,
    email varchar,
    age   int
);

insert into users (name, email, age) values ('testUser', 'test@gmail.com', 20);
insert into users (name, email, age) values ('testUser2', 'test2@gmail.com', 22);
