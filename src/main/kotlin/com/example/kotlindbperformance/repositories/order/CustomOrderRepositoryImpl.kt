package com.example.kotlindbperformance.repositories.order

import com.example.kotlindbperformance.entities.Order
import io.r2dbc.spi.Connection
import kotlinx.coroutines.reactive.collect
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.stream.Collectors

open class CustomOrderRepositoryImpl(private val databaseClient: DatabaseClient): CustomOrderRepository {
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
    @Transactional
    override fun save(order: Order): Mono<Int> {

    return databaseClient
        .insert()
        .into("\"order\"")
        .value("id", order.id)
        .fetch()
        .rowsUpdated()
        .then(databaseClient.insert()
            .into("order_user")
            .value("user_id", order.user)
            .value("order_id", order.id).fetch().rowsUpdated())
        .then(order.products.map { product -> databaseClient.insert()
            .into("order_product")
            .value("order_id", order.id)
            .value("product_id", product)
            .fetch().rowsUpdated()}
            .fold(Flux.empty<Int>()) {acc, mono -> acc.mergeWith(mono)}
            .reduce{ a, b -> a + b})

    }
}