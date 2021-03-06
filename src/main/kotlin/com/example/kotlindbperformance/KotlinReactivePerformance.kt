package com.example.kotlindbperformance

import com.example.kotlindbperformance.repositories.order.OrderRepository
import com.example.kotlindbperformance.repositories.product.ProductRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class KotlinReactivePerformance() {

	@Bean
	fun test(orderRepository: OrderRepository, productRepository: ProductRepository): CommandLineRunner {
		return CommandLineRunner {
			print("Completed")
		}
	}
}

fun main(args: Array<String>) {
	runApplication<KotlinReactivePerformance>(*args)
}
