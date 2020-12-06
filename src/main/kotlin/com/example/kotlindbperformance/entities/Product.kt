package com.example.kotlindbperformance.entities

import org.springframework.data.annotation.Id
import kotlin.math.absoluteValue
import kotlin.random.Random

class Product(
        @Id
        val id: Long = Random.nextLong().absoluteValue,
        val name: String
)