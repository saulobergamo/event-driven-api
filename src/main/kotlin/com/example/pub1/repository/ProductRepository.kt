package com.example.pub1.repository

import com.example.pub1.model.entity.Product
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductRepository : MongoRepository<Product, String> {

    fun findByAvailable(available: Boolean): List<Product>?
}
