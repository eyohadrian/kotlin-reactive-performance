package com.example.kotlindbperformance.controllers

import com.example.kotlindbperformance.entities.Product
import com.example.kotlindbperformance.repositories.product.ProductRepository
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/products")
class ProductController(val productRepository: ProductRepository) {

    @GetMapping
    fun getAll() = productRepository.findAll()

    @PostMapping("/new")
    fun newProduct(@RequestBody product: RequestProduct): Mono<Int> {
        val p = Product(name = product.name)
        return productRepository.save(p)
    }


    data class RequestProduct(val name: String)
}