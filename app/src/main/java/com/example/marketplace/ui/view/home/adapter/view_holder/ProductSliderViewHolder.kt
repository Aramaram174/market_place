package com.example.marketplace.ui.view.home.adapter.view_holder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.data.db.model.HomeData
import com.example.marketplace.databinding.SlideItemContainerBinding
import com.example.marketplace.ui.view.home.adapter.SliderAdapter

class ProductSliderViewHolder(private var binding: SlideItemContainerBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(homeData: HomeData){
        val sliderAdapter = SliderAdapter((homeData as HomeData.ImageSlider).imageSliderItem)
        binding.rvSlider.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            hasFixedSize()
            itemAnimator = null
            adapter = sliderAdapter
        }
    }
}