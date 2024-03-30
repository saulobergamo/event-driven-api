package com.example.pub1.model

data class ProductRequest(
    val productId: String,
    val description: String? = null,
    val amount: Int? = 0
)
