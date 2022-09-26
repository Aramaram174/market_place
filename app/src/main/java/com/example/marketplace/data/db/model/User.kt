package com.example.marketplace.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id") var id: Int? = null,
    @SerializedName("first_name") var firstName: String? = null,
    @SerializedName("last_name") var lastName: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("phone_number_1") var phoneNumber1: String? = null,
    @SerializedName("phone_number_2") var phoneNumber2: String? = null,
    @SerializedName("token") var token: String? = null
)


//    val age: Int,
//    @Expose
//    val gender: Int,
//    @Expose
//    val imageUrl: String = "",
//    @Expose
//    val email: String,
//    @Expose
//    val dateOfBirth: String = "",
//    @Expose
//    val token: String = "",
//    @Expose
//    var bonusBalance: String = "",
//    @Expose
//    val currency: String = "",
//    @Expose
//    val isVerified: Boolean = false
