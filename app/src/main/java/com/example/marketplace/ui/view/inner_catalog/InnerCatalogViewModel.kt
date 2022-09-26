package com.example.marketplace.ui.view.inner_catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.data.repository.MarketRepositoryRoom
import com.example.marketplace.ui.view.catalog.CatalogRepo
import com.example.marketplace.ui.view.sort.SortType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class InnerCatalogViewModel(
    private var repositoryRoom: MarketRepositoryRoom,
    private var repositoryNetwork: MarketRepositoryNetwork
) : ViewModel(), CoroutineScope {

    private val job = SupervisorJob()
    private val catalogRepo: CatalogRepo = CatalogRepo(repositoryRoom, repositoryNetwork)

    fun getProductsByCategory(categoryId: String, limit: String, offset: String) {
        viewModelScope.launch {
            catalogRepo.getProductsByCategory(categoryId, limit, offset)
        }
    }

    fun getSortedFilteredProductsByCategory(categoryId: String, sortType: SortType, limit: String, offset: String) {
        viewModelScope.launch {
            catalogRepo.getSortedProductsByCategory(categoryId, sortType, limit, offset)
        }
    }

    fun productsLiveData() = catalogRepo.productsLiveData

    fun errorLiveData() = catalogRepo.errorLiveData

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    override val coroutineContext: CoroutineContext = job + Dispatchers.IO
}