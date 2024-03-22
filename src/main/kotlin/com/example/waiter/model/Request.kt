package com.example.waiter.model

data class Request(
    val available: Boolean,
    val flavor: String? = null,
    val weight: Double? = null,
    val description: String? = null,
    val amount: Int? = 0
)
