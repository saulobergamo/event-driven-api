package com.example.pub1.producer

import com.example.pub1.model.ProductRequest
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class RabbitmqProducer(
    private val rabbitTemplate: RabbitTemplate,
    private val objectMapper: ObjectMapper
) {
    private val logger = KotlinLogging.logger {}

    @Value("\${spring.rabbitmq.name.exchange}")
    private lateinit var exchange: String

//    @Value("\${spring.rabbitmq.queue.routing.key}")
//    private lateinit var routingKey : String

    fun sendProductRequest(productRequest: ProductRequest) {

        val message = objectMapper.writeValueAsString(productRequest)
        logger.info {
            "sendPlacedOrders: trying to send order=$message"
        }
        rabbitTemplate.convertAndSend(exchange, "", message).also {
            logger.info {
                "sendPlacedOrders: order placed to exchange=$exchange " +
                    "- productDescription=${productRequest.description}"
            }
        }
    }
}
