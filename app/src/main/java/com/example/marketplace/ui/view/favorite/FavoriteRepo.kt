package com.example.marketplace.ui.view.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.data.repository.MarketRepositoryRoom
import com.example.marketplace.data.db.model.Product
import com.example.marketplace.data.db.model.ProductResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FavoriteRepo(
    private var repositoryRoom: MarketRepositoryRoom,
    private var repositoryNetwork: MarketRepositoryNetwork
) : CoroutineScope {

    private val job = SupervisorJob()

    private val _favoriteLiveData: MutableLiveData<ProductResponse> = MutableLiveData()
    val favoriteLiveData: LiveData<ProductResponse> = _favoriteLiveData

    private val _favoriteErrorLiveData: MutableLiveData<String> = MutableLiveData()
    val favoriteErrorLiveData: LiveData<String> = _favoriteErrorLiveData

    fun getFavorite(token:String, limit: String, offset: String) {
        launch {
            val response = repositoryNetwork.getFavorites(token, limit, offset)

            if (response.isSuccessful) {
                response.body()?.let {
                    _favoriteLiveData.postValue(it)
                } ?: run {
                    _favoriteErrorLiveData.postValue(response.message())
                }
            } else {
                _favoriteErrorLiveData.postValue(response.message())
            }
        }
    }

    fun addProductToFavorites(token: String, product: Product) {
        val productIdBody = JsonObject()
        productIdBody.addProperty("product_id", product.id)

        launch {
            val response = repositoryNetwork.addProductToFavorites(token, productIdBody)

            if (response.isSuccessful) {
                response.body()?.let {
                    getFavorite(token, "20", "20")
//                    _favoriteLiveData.value?.results?.add(product)
                } ?: run {
                    _favoriteErrorLiveData.postValue(response.message())
                }
            } else {
                _favoriteErrorLiveData.postValue(response.message())
            }
        }
    }

    fun deleteProductFromFavorites(token: String, id: Int) {
        launch {
            val response = repositoryNetwork.deleteProductFromFavorites(token, id)

            if (response.isSuccessful) {
                response.body()?.let {
                    _favoriteLiveData.value?.results?.removeIf { it.id == id }
                } ?: run {
                    _favoriteErrorLiveData.postValue(response.message())
                }
            } else {
                _favoriteErrorLiveData.postValue(response.message())
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}