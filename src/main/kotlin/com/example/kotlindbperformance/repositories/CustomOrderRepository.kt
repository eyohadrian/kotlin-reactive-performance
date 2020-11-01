package com.example.kotlindbperformance.repositories

import com.example.kotlindbperformance.entities.Order
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomOrderRepository {
    fun findAll(): Flux<Order>
}