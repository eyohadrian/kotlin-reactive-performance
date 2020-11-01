package com.example.kotlindbperformance.repositories

import com.example.kotlindbperformance.entities.Order
import com.example.kotlindbperformance.entities.Product
import org.springframework.data.r2dbc.core.DatabaseClient
import reactor.core.publisher.Flux
import java.util.function.BiFunction

class CustomOrderRepositoryImpl(private val databaseClient: DatabaseClient): CustomOrderRepository {

    override fun findAll(): Flux<Order> {
        val sql_orders = "select o.id, o.name from \"order\" o"
        val sql_products = "select id, name, order_id from product"

        val orders = databaseClient
                .execute(sql_orders)
                .map { row ->
                    OrderDTO(row.get("id") as Int, row.get("name") as String)
                }.all()

        val products = databaseClient
                .execute(sql_products)
                .map{row -> ProductDTO(row.get("id") as Int, row.get("name") as String, row.get("order_id") as Int)}
                .all()

        return orders.zipWith(products.collectList()).map { tuple ->
            Order(tuple.t1.id, tuple.t1.name, tuple.t2.filter { p -> p.orderId == tuple.t1.id }.map { p -> Product(p.id, p.name) }.toMutableSet() ) }
    }

    data class ProductDTO(val id: Int, val name: String, val orderId: Int)
    data class OrderDTO(val id: Int, val name: String)
}