select count(*) from order_user;


start transaction;

SET CONSTRAINTS ALL DEFERRED;

insert into order_user(user_id, order_id) values (1, 12);
insert into order_product(order_id, product_id) values (12, 1);
insert into "order" (id) values (12);

commit transaction;


