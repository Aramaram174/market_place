package com.example.marketplace.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marketplace.data.db.model.Order
import com.example.marketplace.data.db.model.Product
import com.example.marketplace.data.db.model.User

@Database(entities = [User::class, Product::class, Order::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun marketDao(): MarketDao
}