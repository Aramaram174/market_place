package com.example.marketplace.ui.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.marketplace.R
import com.example.marketplace.data.db.model.HomeData
import com.example.marketplace.databinding.SlideCircleItemBinding
import com.example.marketplace.databinding.SlideItemBinding
import com.example.marketplace.databinding.SlideItemContainerBinding

class SliderCircleAdapter(private var adapterData: List<HomeData.CircleImageSliderItem>) : RecyclerView.Adapter<SliderCircleAdapter.MyViewHolder>() {

    companion object{
        @BindingAdapter("circleImageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, url: String?) {
            if (url != null) {
//                val sportIconUrl = "${AppConfigRepo.imageUrls.sportsBaseUrl}$url.png"
                val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
//                Glide.with(view).load(sportIconUrl).apply(requestOptions).centerCrop().into(view)
                Glide.with(view).load(url)
                    .circleCrop()
                    .apply(requestOptions).into(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(SlideCircleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.item = adapterData[position]
        holder.binding.position = position
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return adapterData.size
    }

    class MyViewHolder(val binding: SlideCircleItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}