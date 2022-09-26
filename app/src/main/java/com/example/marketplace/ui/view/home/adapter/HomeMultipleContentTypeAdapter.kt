package com.example.marketplace.ui.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.data.db.model.HomeData
import com.example.marketplace.databinding.BannerViewBinding
import com.example.marketplace.databinding.CircleImageViewBinding
import com.example.marketplace.databinding.SlideItemContainerBinding
import com.example.marketplace.ui.view.home.adapter.view_holder.BannerViewHolder
import com.example.marketplace.ui.view.home.adapter.view_holder.CircleImageViewHolder
import com.example.marketplace.ui.view.home.adapter.view_holder.ProductSliderViewHolder
import com.example.marketplace.ui.view.home.adapter.view_holder.ViewPagerViewHolder

class HomeMultipleContentTypeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_IMAGE_SLIDER = 0
        private const val TYPE_CIRCLE_IMAGE_SLIDER = 1
        private const val TYPE_IMAGE_BANNER = 2
        private const val TYPE_PRODUCT_SLIDER = 3
    }

    private val multipleContentTypeData = mutableListOf<HomeData>()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {
            TYPE_IMAGE_SLIDER -> (holder as ViewPagerViewHolder).bind(multipleContentTypeData[position])
            TYPE_CIRCLE_IMAGE_SLIDER -> (holder as CircleImageViewHolder).bind(
                multipleContentTypeData[position]
            )
            TYPE_IMAGE_BANNER -> (holder as BannerViewHolder).bind(multipleContentTypeData[position])
            TYPE_PRODUCT_SLIDER -> (holder as ProductSliderViewHolder).bind(multipleContentTypeData[position])
            else -> throw IllegalArgumentException("${this@HomeMultipleContentTypeAdapter::class.java} does not have ViewHolder with type of ${holder.itemViewType}")
        }

    override fun getItemCount(): Int = multipleContentTypeData.size

    override fun getItemViewType(position: Int): Int =
        when (multipleContentTypeData[position]) {
            is HomeData.ImageSlider -> TYPE_IMAGE_SLIDER
            is HomeData.CircleImageSlider -> TYPE_CIRCLE_IMAGE_SLIDER
            is HomeData.ImageBanner -> TYPE_IMAGE_BANNER
            is HomeData.ProductSlider -> TYPE_PRODUCT_SLIDER
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_IMAGE_SLIDER -> ViewPagerViewHolder(
            SlideItemContainerBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
        TYPE_CIRCLE_IMAGE_SLIDER -> CircleImageViewHolder(
            CircleImageViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        TYPE_IMAGE_BANNER -> BannerViewHolder(
            BannerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        TYPE_PRODUCT_SLIDER -> ProductSliderViewHolder(
            SlideItemContainerBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
        else -> throw IllegalArgumentException("${this@HomeMultipleContentTypeAdapter::class.java} does not have ViewHolder with type of $viewType")
    }

    fun setData(data: List<HomeData>) {
        multipleContentTypeData.apply {
            clear()
            addAll(data)
        }
    }
}

