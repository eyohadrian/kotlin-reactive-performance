create extension if not exists "uuid-ossp";

drop table if exists "order" cascade;
create table "order"(
    id bigint primary key
);

drop table if exists domain_user cascade;
create table domain_user(
    id bigint primary key ,
    name varchar(191)
);

drop table if exists order_user cascade;
create table order_user(
    user_id bigint,
    order_id bigint,
    primary key (user_id, order_id),
    constraint "user_fk" foreign key (user_id) references domain_user(id) on delete cascade deferrable INITIALLY DEFERRED,
    constraint "order_id" foreign key (order_id) references "order"(id) on delete cascade deferrable INITIALLY DEFERRED
);

drop table if exists product cascade;
create table product(
   id bigint primary key,
   name varchar(191)
);


drop table if exists order_product cascade;
create table order_product(
    order_id bigint not null ,
    product_id bigint not null,
    primary key (order_id, product_id),
    constraint "order_fk" foreign key (order_id) references "order"(id) on delete cascade deferrable INITIALLY DEFERRED,
    constraint "product_fk" foreign key (product_id) references product(id) on delete cascade deferrable INITIALLY DEFERRED
);


insert into product
values (1, 'Bread'),
       (3, 'Milk'),
       (4, 'Eggs'),
       (5, 'Chocolate'),
       (6, 'Beacon'),
       (7, 'Orange juice'),
       (2, 'Butter'),
       (8, 'Water');

insert into domain_user (id, name)
values (1, 'Nacor'), (2, 'Juan'), (3, 'Xie Xie');