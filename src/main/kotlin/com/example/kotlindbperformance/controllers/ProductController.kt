package com.example.kotlindbperformance.controllers

import com.example.kotlindbperformance.repositories.ProductRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController(val productRepository: ProductRepository) {

    @GetMapping
    fun getAll() = productRepository.findAll()

}