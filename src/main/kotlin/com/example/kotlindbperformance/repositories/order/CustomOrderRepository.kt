package com.example.kotlindbperformance.repositories.order

import com.example.kotlindbperformance.entities.Order
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomOrderRepository {
    fun save(order: Order): Mono<Int>
}