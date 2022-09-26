package com.example.marketplace.ui.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.marketplace.data.db.model.HomeData
import com.example.marketplace.databinding.SlideItemBinding

class SliderAdapter(private var adapterData: List<HomeData.ImageSliderItem>) : RecyclerView.Adapter<SliderAdapter.MyViewHolder>() {

    companion object{
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, url: String?) {
            if (url != null) {
//                val sportIconUrl = "${AppConfigRepo.imageUrls.sportsBaseUrl}$url.png"
                val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
//                Glide.with(view).load(sportIconUrl).apply(requestOptions).centerCrop().into(view)
                Glide.with(view).load(url).apply(requestOptions).centerCrop().into(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(SlideItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.item = adapterData[position]
        holder.binding.position = position
//        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return adapterData.size
    }

    class MyViewHolder(val binding: SlideItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}