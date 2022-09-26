package com.example.marketplace.ui.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marketplace.data.db.model.ProductResponse
import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.data.repository.MarketRepositoryRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SearchRepo(
    private var repositoryRoom: MarketRepositoryRoom,
    private var repositoryNetwork: MarketRepositoryNetwork
) : CoroutineScope {

    private val job = SupervisorJob()

    private val _productsLiveData: MutableLiveData<ProductResponse> = MutableLiveData()
    val productsLiveData: LiveData<ProductResponse> = _productsLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun getSearchedProduct(query: String, limit: String, offset: String) {
        launch {
            val response = repositoryNetwork.getSearchedProduct(1, query, limit, offset)

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