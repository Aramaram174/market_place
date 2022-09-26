package com.example.marketplace.ui.view.home.adapter.view_holder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.data.db.model.HomeData
import com.example.marketplace.databinding.CircleImageViewBinding
import com.example.marketplace.ui.view.home.adapter.SliderCircleAdapter

class CircleImageViewHolder(private var binding: CircleImageViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(homeData: HomeData) {
        val slideCirAdapter =
            SliderCircleAdapter((homeData as HomeData.CircleImageSlider).circleImageSliderItem)
        binding.rvCircle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            hasFixedSize()
            itemAnimator = null
            adapter = slideCirAdapter
        }
    }
}