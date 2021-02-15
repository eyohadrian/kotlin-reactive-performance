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