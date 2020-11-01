package com.example.kotlindbperformance

import com.example.kotlindbperformance.repositories.OrderRepository
import com.example.kotlindbperformance.repositories.ProductRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class KotlinReactivePerformance() {

	@Bean
	fun test(orderRepository: OrderRepository, productRepository: ProductRepository): CommandLineRunner {
		return CommandLineRunner {
			val product = orderRepository.findAll().collectList().block()
			print("Completed")
		}
	}
}

fun main(args: Array<String>) {
	runApplication<KotlinReactivePerformance>(*args)
}
