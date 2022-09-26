package com.example.marketplace.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat

fun getStringResourceByName(context: Context, name: String): String {
    val packageName: String? = context.packageName
    val resId = context.resources.getIdentifier(name, "string", packageName)
    return context.getString(resId)
}

fun getDrawableResourceByName(context: Context, name: String): Drawable? {
    val packageName: String? = context.packageName
    val resId = context.resources.getIdentifier(name, "drawable", packageName)
    return ResourcesCompat.getDrawable(context.resources, resId, null)
}