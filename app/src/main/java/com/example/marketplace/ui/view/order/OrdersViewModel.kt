package com.example.marketplace.ui.view.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.data.repository.MarketRepositoryRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class OrdersViewModel(
    private var repositoryRoom: MarketRepositoryRoom,
    private var repositoryNetwork: MarketRepositoryNetwork
) : ViewModel(), CoroutineScope {

    private val ordersRepo: OrdersRepo = OrdersRepo(repositoryRoom, repositoryNetwork)
    private val job = SupervisorJob()

    init {
        getOrders()
    }

    private fun getOrders() {
        viewModelScope.launch {
            ordersRepo.getOrders()
        }
    }

    fun ordersLiveData() = ordersRepo.ordersLiveData

    fun ordersErrorLiveData() = ordersRepo.ordersErrorLiveData

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    override val coroutineContext: CoroutineContext = job + Dispatchers.IO
}