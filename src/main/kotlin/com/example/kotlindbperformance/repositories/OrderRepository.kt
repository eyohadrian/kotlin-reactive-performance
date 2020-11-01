package com.example.kotlindbperformance.repositories

import com.example.kotlindbperformance.entities.Order
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.util.*

@Repository
interface OrderRepository: ReactiveCrudRepository<Order, UUID>, CustomOrderRepository {

}