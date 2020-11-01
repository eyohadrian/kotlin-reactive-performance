package com.example.kotlindbperformance.entities

import org.springframework.data.annotation.Id

class Product(
        @Id
        val id: Int,
        val name: String
)