package com.example.marketplace.ui.view.bottom_navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BottomNavigationViewModel : ViewModel() {

    private val _bottomNavVisible = MutableLiveData(true)
    val bottomNavVisible: LiveData<Boolean> = _bottomNavVisible

    private val _menuItem = MutableLiveData<String>()
    val menuItem: LiveData<String> = _menuItem

    fun setBottomNavMode(bottomNavVisible: Boolean) {
        _bottomNavVisible.postValue(bottomNavVisible)
    }

    fun setMenuItem(item: String) {
        _menuItem.value = item
    }
}