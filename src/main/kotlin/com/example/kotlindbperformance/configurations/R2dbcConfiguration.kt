package com.example.kotlindbperformance.configurations

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.spi.ConnectionFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager
import org.springframework.transaction.ReactiveTransactionManager


@Configuration
@EnableTransactionManagement
class R2dbcConfiguration: AbstractR2dbcConfiguration() {

    @Bean(name = [ "SpringConnectionFactory"])
    override fun connectionFactory(): ConnectionFactory {
        return PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
            .database("reactive_performance")
            .host("127.0.0.1")
            .port(5432)
            .username("postgres")
            .password("c0c0l0c0")
            .build())
    }

    @Bean
    fun transactionManager(@Qualifier("SpringConnectionFactory") connectionFactory: ConnectionFactory): ReactiveTransactionManager {
        return R2dbcTransactionManager(connectionFactory)
    }
}