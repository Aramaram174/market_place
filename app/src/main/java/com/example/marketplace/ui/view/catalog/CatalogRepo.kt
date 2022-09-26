package com.example.marketplace.ui.view.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.data.repository.MarketRepositoryRoom
import com.example.marketplace.data.db.model.Category
import com.example.marketplace.data.db.model.ProductResponse
import com.example.marketplace.ui.view.sort.SortType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CatalogRepo(
    private var repositoryRoom: MarketRepositoryRoom,
    private var repositoryNetwork: MarketRepositoryNetwork
) : CoroutineScope {

    private val job = SupervisorJob()

    private val _catalogLiveData: MutableLiveData<List<Category>> = MutableLiveData()
    val catalogLiveData: LiveData<List<Category>> = _catalogLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    private val _productsLiveData: MutableLiveData<ProductResponse> = MutableLiveData()
    val productsLiveData: LiveData<ProductResponse> = _productsLiveData

    fun getCatalog() {
        launch {
            val response = repositoryNetwork.getCatalog(1)

            if (response.isSuccessful) {
                response.body()?.let {
                    _catalogLiveData.postValue(it)
                    repositoryRoom.setCatalog(it)
                } ?: run {
                    _errorLiveData.postValue(response.message())
                }
            } else {
                _errorLiveData.postValue(response.message())
            }
        }
    }

    fun getProductsByCategory(categoryId: String, limit: String, offset: String) {
        launch {
            val response = repositoryNetwork.getProductsByCategory(1, categoryId, limit, offset)

            if (response.isSuccessful) {
                response.body()?.let {
                    _productsLiveData.postValue(it)
                } ?: run {
                    _errorLiveData.postValue(response.message())
                }
            } else {
                _errorLiveData.postValue(response.message())
            }
        }
    }

    fun getSortedProductsByCategory(categoryId: String, sortType: SortType, limit: String, offset: String) {
        launch {
            val response = repositoryNetwork.getSortedProductsByCategory(1, categoryId, sortType, limit, offset)

            if (response.isSuccessful) {
                response.body()?.let {
                    _productsLiveData.postValue(it)
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