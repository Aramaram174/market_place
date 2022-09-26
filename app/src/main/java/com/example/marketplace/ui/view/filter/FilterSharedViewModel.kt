package com.example.marketplace.ui.view.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketplace.data.db.model.MultiselectAdapterItem

class FilterSharedViewModel : ViewModel(){

    private val _productType = MutableLiveData<List<MultiselectAdapterItem>>()
    val productType: LiveData<List<MultiselectAdapterItem>> = _productType

    private val _color = MutableLiveData<List<MultiselectAdapterItem>>()
    val color: LiveData<List<MultiselectAdapterItem>> = _color

    private val _brand = MutableLiveData<List<MultiselectAdapterItem>>()
    val brand: LiveData<List<MultiselectAdapterItem>> = _brand

    private val _size = MutableLiveData<List<MultiselectAdapterItem>>()
    val size: LiveData<List<MultiselectAdapterItem>> = _size

    private val _price = MutableLiveData<Pair<Int, Int>>()
    val price: LiveData<Pair<Int, Int>> = _price

    fun setProductType(multiselectAdapterItems: List<MultiselectAdapterItem>) = _productType.postValue(multiselectAdapterItems)
    fun setColor(multiselectAdapterItems: List<MultiselectAdapterItem>) = _color.postValue(multiselectAdapterItems)
    fun setBrand(multiselectAdapterItems: List<MultiselectAdapterItem>) = _brand.postValue(multiselectAdapterItems)
    fun setSize(multiselectAdapterItems: List<MultiselectAdapterItem>) = _size.postValue(multiselectAdapterItems)
    fun setPrice(minPrice: Int, maxPrice: Int) = _price.postValue(Pair(minPrice, maxPrice))

    fun clearFilters(){
        _productType.postValue(listOf())
        _color.postValue(listOf())
        _brand.postValue(listOf())
        _size.postValue(listOf())
        _price.postValue(Pair(1, 1000))
    }
}