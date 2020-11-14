package com.example.kotlindbperformance.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("domain_user")
class User(
        @Id
        val id: Long,
        val name: String
)