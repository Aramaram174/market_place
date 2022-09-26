package com.example.marketplace.ui.view.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.data.repository.MarketRepositoryRoom
import com.example.marketplace.data.db.model.ProductResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CartRepo(
    private var repositoryRoom: MarketRepositoryRoom,
    private var repositoryNetwork: MarketRepositoryNetwork
) : CoroutineScope {

    private val job = SupervisorJob()

    private val _cartLiveData: MutableLiveData<ProductResponse> = MutableLiveData()
    val cartLiveData: LiveData<ProductResponse> = _cartLiveData

    private val _deleteCartItemLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val deleteCartItemLiveData: LiveData<Boolean> = _deleteCartItemLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun getCart(token: String, limit: String, offset: String) {
        launch {
            val response = repositoryNetwork.getCart(token, limit, offset)

            if (response.isSuccessful) {
                response.body()?.let {
                    _cartLiveData.postValue(it)
                } ?: run {
                    _errorLiveData.postValue(response.message())
                }
            } else {
                _errorLiveData.postValue(response.message())
            }
        }
    }

    fun addProductToCart(token: String, id: Int) {
        launch {
            val response = repositoryNetwork.addProductToCart(token, id)

            if (response.isSuccessful) {
                response.body()?.let {
//                    _cartLiveData.postValue(it)
                } ?: run {
                    _errorLiveData.postValue(response.message())
                }
            } else {
                _errorLiveData.postValue(response.message())
            }
        }
    }

    fun deleteProductFromCart(token: String, id: String) {
        launch {
            val response = repositoryNetwork.deleteProductFromCart(token, id)

            if (response.isSuccessful) {
                response.body()?.let {
                    _deleteCartItemLiveData.postValue(true)
                } ?: run {
                    _errorLiveData.postValue(response.message())
                }
            } else {
                _errorLiveData.postValue(response.message())
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}