package com.example.kotlindbperformance.repositories

import com.example.kotlindbperformance.entities.Product
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.*

interface ProductRepository: ReactiveCrudRepository<Product, UUID> {
}