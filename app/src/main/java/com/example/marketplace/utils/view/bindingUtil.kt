package com.example.marketplace.utils.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
//                val sportIconUrl = "${AppConfigRepo.imageUrls.sportsBaseUrl}$url.png"
        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
//                Glide.with(view).load(sportIconUrl).apply(requestOptions).centerCrop().into(view)
        Glide.with(view).load("https://images.unsplash.com/photo-1621616875450-79f024a8c42c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080").apply(requestOptions).centerCrop().into(view)
    }
}