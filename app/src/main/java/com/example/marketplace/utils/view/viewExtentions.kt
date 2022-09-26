package com.example.marketplace.utils.view

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.request.transition.Transition
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget

fun EditText.focus(){
    requestFocus()
    setSelection(length())
}

fun ImageView.loadFromUrl(imageUrl: String) {
    Glide.with(this).load(imageUrl).into(this)
}

fun MenuItem.loadIconFromUrl(context: Context, imageUrl: String) {
    Glide.with(context).asBitmap()
        .load(imageUrl)
        .into(object : SimpleTarget<Bitmap>(100, 100) {
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                val circularIcon = RoundedBitmapDrawableFactory.create(context.resources, resource)
                circularIcon.isCircular = true
                icon = circularIcon
            }
        })
}
