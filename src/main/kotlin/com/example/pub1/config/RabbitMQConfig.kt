package com.example.pub1.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.Queue
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    @Value("\${spring.rabbitmq.name.queue1}")
    private lateinit var queue1: String

    @Value("\${spring.rabbitmq.name.queue2}")
    private lateinit var queue2: String

    @Value("\${spring.rabbitmq.name.queue3}")
    private lateinit var queue3: String

    @Value("\${spring.rabbitmq.name.exchange}")
    private lateinit var exchange: String

//    in case we have a direct exchange its necessary to configure
//    a routing key to bind a queue to a specific exchange
//    @Value("\${spring.rabbitmq.queue.routing.key}")
//    private lateinit var routingKey : String

    @Bean
    fun fanoutExchange(): FanoutExchange {
        return FanoutExchange(exchange)
    }
    @Bean
    fun queue1(): Queue {
        return Queue(queue1)
    }
    @Bean
    fun queue2(): Queue {
        return Queue(queue2)
    }
    @Bean
    fun queue3(): Queue {
        return Queue(queue3)
    }

    @Bean
    fun bindingQueue1ToFanoutExchange(): Binding {
        return BindingBuilder.bind(queue1()).to(fanoutExchange())
    }
    @Bean
    fun bindingQueue2ToFanoutExchange(): Binding {
        return BindingBuilder.bind(queue2()).to(fanoutExchange())
    }
    @Bean
    fun bindingQueue3ToFanoutExchange(): Binding {
        return BindingBuilder.bind(queue3()).to(fanoutExchange())
    }

    @Bean
    fun binding(): BindingBuilder.DestinationConfigurer? {
        return BindingBuilder.bind(fanoutExchange())
    }
}
