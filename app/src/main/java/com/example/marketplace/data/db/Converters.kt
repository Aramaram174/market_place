package com.example.marketplace.data.db

import androidx.room.TypeConverter
import com.example.marketplace.data.db.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun stringToList(value: String) : List<Product>{
        val listType = object : TypeToken<List<Product>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(list: List<Product>): String {
        val listType = object : TypeToken<List<Product>>() {}.type
        return Gson().toJson(list, listType)
    }
}