package com.example.marketplace.ui.view.home.adapter.view_holder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.marketplace.R
import com.example.marketplace.data.db.model.HomeData
import com.example.marketplace.databinding.BannerViewBinding

class BannerViewHolder(private var binding: BannerViewBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(homeData: HomeData){
        setData((homeData as HomeData.ImageBanner).imageUrl[0])
    }

    private fun setData(imageUrl: String) {
        Glide.with(binding.root.context)
            .load(imageUrl)
            .error(R.drawable.ic_delete)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.ivBanner)
    }
}