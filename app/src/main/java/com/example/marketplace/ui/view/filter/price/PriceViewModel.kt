package com.example.marketplace.ui.view.filter.price

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PriceViewModel : ViewModel(){

    private val _minPrice = MutableLiveData<Int>()
    val minPrice: LiveData<Int> = _minPrice

    private val _maxPrice = MutableLiveData<Int>()
    val maxPrice: LiveData<Int> = _maxPrice

    fun setMinPrice(minPrice: Int){
        _minPrice.value = minPrice
    }

    fun setMaxPrice(maxPrice: Int){
        _maxPrice.value = maxPrice
    }

    fun reset(minPrice: Int, maxPrice: Int){
        _minPrice.value = minPrice
        _maxPrice.value = maxPrice
    }
}