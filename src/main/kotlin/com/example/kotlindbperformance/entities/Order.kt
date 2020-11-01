package com.example.kotlindbperformance.entities

import org.springframework.data.annotation.Id


data class Order(
        @Id
        val id: Int,
        val name: String,
        val products: MutableSet<Product>
)