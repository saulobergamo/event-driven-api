package com.example.waiter.repository

import com.example.waiter.model.entity.Popsicole
import org.springframework.data.mongodb.repository.MongoRepository

interface PopsicolesRepository : MongoRepository<Popsicole, String> {

    fun findByAvailable(available: Boolean) : List<Popsicole>?
}