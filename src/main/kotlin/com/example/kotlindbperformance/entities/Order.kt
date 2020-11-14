package com.example.kotlindbperformance.entities

import org.springframework.data.annotation.Id
import kotlin.math.absoluteValue
import kotlin.random.Random


data class Order(
        @Id
        val id: Long = Random.nextLong().absoluteValue,
        val products: MutableSet<Long>,
        val user: Long
)