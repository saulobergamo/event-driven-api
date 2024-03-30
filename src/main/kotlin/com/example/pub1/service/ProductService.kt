package com.example.pub1.service

import com.example.pub1.model.ProductRequest
import com.example.pub1.model.entity.Product
import com.example.pub1.producer.RabbitmqProducer
import com.example.pub1.repository.ProductRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val rabbitmqProducer: RabbitmqProducer
) {
    private val logger = KotlinLogging.logger {}

    fun getAvailableProducts(): List<Product>? {
        return productRepository.findByAvailable(true).also {
            logger.info {
                "getAvailableProducts: trying to get available products"
            }
        }
    }

    fun showAllProducts(): List<Product>? {
        return productRepository.findAll().also {
            logger.info {
                "showAllProducts: trying to get all products"
            }
        }
    }

    fun sendRequestList(requestList: List<ProductRequest>) {
        var count = 0
        requestList.forEach { product ->
            count++
            rabbitmqProducer.sendProductRequest(product)
        }.also {
            logger.info {
                "saveProductsList: saved $count products in dataBase"
            }
        }
    }
}
