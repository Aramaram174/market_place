package com.example.marketplace.data.db.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.marketplace.data.db.Converters
import com.google.gson.annotations.Expose

@Entity(tableName = "order")
@TypeConverters(Converters::class)
data class Order(
    @PrimaryKey(autoGenerate = false)
    @Expose
    @NonNull
    val id: Int,
    @Expose
    @NonNull
    val name: String,
    val orderNumber: Int,
    val orderDate: String,
//    val shippingAddress: Address,
    val status: Int,
    val statusText: String,
    val itemCount: Int,
    val deliveredDate: String,
//    val orderProducts: List<Product>,
    val deliveredMethod: String,
    val shippedDate: String,
    val paymentDetails: String,
    val subTotal: Int,
    val shippingPrice: Int,
    val total: Int
    )