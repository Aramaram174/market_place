package com.example.marketplace.ui.view.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.data.repository.MarketRepositoryRoom
import com.example.marketplace.ui.view.catalog.CatalogRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SearchViewModel(
    private var repositoryRoom: MarketRepositoryRoom,
    private var repositoryNetwork: MarketRepositoryNetwork
) : ViewModel(), CoroutineScope {

    private val job = SupervisorJob()
    private val searchRepo: SearchRepo = SearchRepo(repositoryRoom, repositoryNetwork)

    fun getSearchedProduct(query: String, limit: String, offset: String) {
        viewModelScope.launch {
            searchRepo.getSearchedProduct(query, limit, offset)
        }
    }

    fun productsLiveData() = searchRepo.productsLiveData

    fun errorLiveData() = searchRepo.errorLiveData

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    override val coroutineContext: CoroutineContext = job + Dispatchers.IO
}