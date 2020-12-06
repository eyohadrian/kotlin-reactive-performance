package com.example.kotlindbperformance.repositories.order

import com.example.kotlindbperformance.entities.Order
import io.r2dbc.spi.Connection
import io.r2dbc.spi.ConnectionFactory
import org.springframework.data.r2dbc.core.DatabaseClient
import reactor.core.publisher.Mono

class CustomOrderRepositoryImpl(private val databaseClient: DatabaseClient, val connectionFactory: ConnectionFactory): CustomOrderRepository {
/*
    override fun findAll(): Flux<Order> {
        val sql_orders = "select o.id from \"order\" o"
        val sql_products = "select id, name from product"

        val orders = databaseClient
                .execute(sql_orders)
                .map { row ->
                    OrderDTO(row.get("id") as Long)
                }.all()

        val products = databaseClient
                .execute(sql_products)
                .map{row -> ProductDTO(row.get("id") as Long, row.get("name") as String, row.get("order_id") as Long)}
                .all()

        return orders.zipWith(products.collectList()).map { tuple ->
            Order(tuple.t1.id, tuple.t2.filter { p -> p.orderId = tuple.t1.id }.map { p -> Product(p.id, p.name) }.toMutableSet(), 0 ) }




        databaseClient.execute(insert_order)
                .bind("orderId", order.id)
                .fetch().rowsUpdated()
                .then(databaseClient
                        .execute(insert_user_order)
                        .bind("userId", order.user)
                        .bind("orderId", order.id)
                        .fetch().rowsUpdated())
                .block()

    }
*/
    override fun save(order: Order)  {

        val insert_user_order = "insert into order_user(user_id, order_id) values(:userId, :orderId)"
        val insert_order = "insert into \"order\"(id) values(:orderId)"

        val orderResult = databaseClient.execute(insert_order)
                .bind("orderId", order.id)
                .fetch().rowsUpdated()

        val userOrderResult = databaseClient
                .execute(insert_user_order)
                .bind("userId", order.user)
                .bind("orderId", order.id)
                .fetch().rowsUpdated()

        orderResult.block()
        userOrderResult.block()
        Mono.from(connectionFactory.create())
            .map(Connection::createBatch).map { batch ->
                order.products.forEach{
                    val sql = String.format("insert into order_product(product_id, order_id) values(%s, '%s')", it, order.id)
                    batch.add(sql)
                }
                batch
            }.flatMap { batch -> Mono.from(batch.execute()) }
            .block()
    }

    data class ProductDTO(val id: Long, val name: String, val orderId: Long)
    data class OrderDTO(val id: Long)
}