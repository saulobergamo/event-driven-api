package com.example.pub1.model

data class ProductRequest(
    val available: Boolean,
    val description: String? = null,
    val price: Double? = 0.0,
    val amount: Int? = 0
)
