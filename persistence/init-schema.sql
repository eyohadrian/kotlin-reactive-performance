create extension if not exists "uuid-ossp";

drop table if exists "order" cascade;
create table "order"(
    id int primary key ,
    name varchar(191)
);

drop table if exists product cascade;
create table product(
   id int primary key,
   name varchar(191),
   order_id int not null,
   constraint "order_product_fk" foreign key(order_id) references "order"(id) on delete cascade
);



insert into "order"(id, name) values (1, 'Probando');
insert into product values (1, '1B', 1), (2, '2B', 1),(3, '3B', 1);

