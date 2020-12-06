package com.example.kotlindbperformance.repositories.product

import com.example.kotlindbperformance.entities.Product
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository: ReactiveCrudRepository<Product, UUID>, CustomProductRepository {
}