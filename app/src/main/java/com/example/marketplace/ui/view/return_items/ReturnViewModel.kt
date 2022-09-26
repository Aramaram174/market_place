package com.example.marketplace.ui.view.return_items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.data.repository.MarketRepositoryRoom
import com.example.marketplace.ui.view.order.OrdersRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ReturnViewModel(
    private var repositoryRoom: MarketRepositoryRoom,
    private var repositoryNetwork: MarketRepositoryNetwork
) : ViewModel(), CoroutineScope {

    private val ordersRepo: OrdersRepo = OrdersRepo(repositoryRoom, repositoryNetwork)
    private val job = SupervisorJob()

    init {
        getReturns()
    }

    private fun getReturns() {
        viewModelScope.launch {
            ordersRepo.getOrders()
        }
    }

    fun returnsLiveData() = ordersRepo.ordersLiveData

    fun returnsErrorLiveData() = ordersRepo.ordersErrorLiveData

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    override val coroutineContext: CoroutineContext = job + Dispatchers.IO
}