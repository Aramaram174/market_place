package com.example.marketplace.data.db.model

data class Address(
    val id: Int,
    val contact: User,
    val country: String,
    val addressOne: String,
    val addressTwo: String,
    val city: String,
    val state: String,
    val zipCode: Int,
    val isDefaultDelivery: Boolean,
    val isDefaultBilling: Boolean
)
