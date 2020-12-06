package com.example.kotlindbperformance.repositories.product

import com.example.kotlindbperformance.entities.Order
import com.example.kotlindbperformance.entities.Product
import io.r2dbc.spi.Connection
import io.r2dbc.spi.ConnectionFactory
import org.springframework.data.r2dbc.core.DatabaseClient
import reactor.core.publisher.Mono

class CustomProductRepositoryImpl(private val databaseClient: DatabaseClient, val connectionFactory: ConnectionFactory): CustomProductRepository {

    override fun save(product: Product): Mono<Int> {

        val insert_product = "insert into product(id, name) values(:id, :name)"

        return databaseClient
                .execute(insert_product)
                .bind("id", product.id)
                .bind("name", product.name)
                .fetch().rowsUpdated()
    }
}