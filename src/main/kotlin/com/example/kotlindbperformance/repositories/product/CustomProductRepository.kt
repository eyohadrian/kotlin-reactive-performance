package com.example.kotlindbperformance.repositories.product

import com.example.kotlindbperformance.entities.Product
import reactor.core.publisher.Mono

interface CustomProductRepository {
    fun save(product: Product): Mono<Int>
}