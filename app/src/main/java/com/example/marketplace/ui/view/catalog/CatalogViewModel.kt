package com.example.marketplace.ui.view.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.data.repository.MarketRepositoryRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CatalogViewModel(
    private var repositoryRoom: MarketRepositoryRoom,
    private var repositoryNetwork: MarketRepositoryNetwork
) : ViewModel(),
    CoroutineScope {

    private val job = SupervisorJob()
    private val catalogRepo: CatalogRepo = CatalogRepo(repositoryRoom, repositoryNetwork)

    init {
        getCatalog()
    }

    private fun getCatalog() {
        viewModelScope.launch {
            catalogRepo.getCatalog()
        }
    }

    fun catalogLiveData() = catalogRepo.catalogLiveData

    fun errorLiveData() = catalogRepo.errorLiveData

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    override val coroutineContext: CoroutineContext = job + Dispatchers.IO
}