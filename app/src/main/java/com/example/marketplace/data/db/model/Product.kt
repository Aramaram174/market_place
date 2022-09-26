package com.example.marketplace.data.db.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.marketplace.data.db.Converters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ProductResponse(
    @SerializedName("count")
    @Expose
    val count: Int,
    @SerializedName("next")
    @Expose
    val next: String,
    @SerializedName("previous")
    @Expose
    val previous: String,
    @SerializedName("results")
    @Expose
    val results: MutableList<Product>
)

@Parcelize
@Entity(tableName = "products")
@TypeConverters(Converters::class)
data class Product(
    @PrimaryKey(autoGenerate = false)
    @Expose
    @NonNull
    val id: Int,
    @Expose
    @NonNull
    val name: String,
    @Expose
    @NonNull
    val price: Int,
    val saleType: Int,
    val sale: Int,
    val salePrice: Int,
    @Expose
    @NonNull
    val sizeType: Int,
    @Expose
    val size: String,
    val description: String,
    var isFavorite: Boolean,
    val isCartItem: Boolean,
//    val image: List<String>,
    val color: String,
    val deliveryPrice: Boolean,
    val isReturnedItem: Boolean,
    val returnPrice: Int,
//    val review: Review,
    val countInStock: String,
//    val productInformation: ProductInformation
): Parcelable {
    @Ignore
    constructor() : this(-1, "", 0, 0, 0, 0, 0, "", "", false, false,
        "", false, false, 0, "")

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (id != other.id) return false
        if (name != other.name) return false
        if (price != other.price) return false
        if (saleType != other.saleType) return false
        if (sale != other.sale) return false
        if (salePrice != other.salePrice) return false
        if (sizeType != other.sizeType) return false
        if (size != other.size) return false
        if (description != other.description) return false
        if (isFavorite != other.isFavorite) return false
        if (isCartItem != other.isCartItem) return false
        if (color != other.color) return false
        if (deliveryPrice != other.deliveryPrice) return false
        if (isReturnedItem != other.isReturnedItem) return false
        if (returnPrice != other.returnPrice) return false
        if (countInStock != other.countInStock) return false

        return true
    }
}

data class Review(
    val id: Int
)

data class ProductInformation(
    val id: Int
)
