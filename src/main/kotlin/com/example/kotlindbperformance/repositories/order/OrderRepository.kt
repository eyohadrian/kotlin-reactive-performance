package com.example.kotlindbperformance.repositories.order

import com.example.kotlindbperformance.entities.Order
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderRepository: ReactiveCrudRepository<Order, UUID>, CustomOrderRepository {

}