package com.example.waiter.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Popsicole(
    @Id
    var id: String? = null,

    @Indexed
    var available: Boolean,
    var flavor: String? = null,
    var weight: Double? = null,
    var description: String? = null,
    var amount: Int? = 0
)