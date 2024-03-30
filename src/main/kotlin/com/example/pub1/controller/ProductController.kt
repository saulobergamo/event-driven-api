package com.example.pub1.controller

import com.example.pub1.model.ProductRequest
import com.example.pub1.model.entity.Product
import com.example.pub1.producer.RabbitmqProducer
import com.example.pub1.service.ProductService
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "ProductController")
@RequestMapping("/event-driven-api-pub1")
class ProductController(
    private val productService: ProductService,
    private val rabbitmqProducer: RabbitmqProducer
) {
    private val logger = KotlinLogging.logger {}

    @GetMapping("/available")
    fun getAvailableMenu(): List<Product>? {
        return productService.getAvailableProducts()
    }

    @GetMapping("/all")
    fun getAllProducts(): List<Product>? {
        return productService.showAllProducts()
    }
    @PostMapping("/requestList")
    fun sendRequestList(@RequestBody requestList: List<ProductRequest>): ResponseEntity<String> {
        productService.sendRequestList(requestList).also {
            logger.info {
                "sendRequestList: requestList sent successfully"
            }
        }
        return ResponseEntity.ok("placeOrder: order placed correctly")
    }

    @PostMapping("/productRequest")
    fun sendProductRequest(@RequestBody productRequest: ProductRequest): ResponseEntity<String> {
        rabbitmqProducer.sendProductRequest(productRequest)
        return ResponseEntity.ok("sendProductRequest: message sent successfully, message=$productRequest")
    }
}
