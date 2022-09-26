package com.example.marketplace.data.db.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Category(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("image")
    @Expose
    var imageURL: String ,
    @SerializedName("sub_categories")
    @Expose
    val subCategories: @RawValue List<Category>?
): Parcelable{
    override fun hashCode(): Int {
        return super.hashCode()
    }
}
