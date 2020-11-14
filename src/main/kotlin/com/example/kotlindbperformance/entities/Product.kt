package com.example.kotlindbperformance.entities

import org.springframework.data.annotation.Id

class Product(
        @Id
        val id: Long,
        val name: String
)