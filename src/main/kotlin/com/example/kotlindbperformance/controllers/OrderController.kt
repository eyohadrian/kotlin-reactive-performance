package com.example.kotlindbperformance.controllers

import com.example.kotlindbperformance.entities.Order
import com.example.kotlindbperformance.repositories.OrderRepository
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/orders")
class OrderController(val orderRepository: OrderRepository) {

    @GetMapping
    fun get(): Flux<Order> = orderRepository.findAll()

    @PostMapping("/new")
    fun new(@RequestBody request: EntityDTO) {
        val order = Order(products = request.products, user = request.userId )
        orderRepository.save(order)
    }

    @PostMapping("/test")
    fun test(@RequestBody request: EntityDTO) {
        print(request.userId)
    }

    data class EntityDTO(val userId: Long, val products: MutableSet<Long>)
}