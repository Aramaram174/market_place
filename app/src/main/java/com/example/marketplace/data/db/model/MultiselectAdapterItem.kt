package com.example.marketplace.data.db.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MultiselectAdapterItem(
    val id: Int,
    val name: String,
    var isChecked: Boolean = false
): Parcelable

@Parcelize
class MultiselectAdapterItems: ArrayList<MultiselectAdapterItem>(), Parcelable