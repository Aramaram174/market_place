package com.example.marketplace.ui.view.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marketplace.data.db.model.Order
import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.data.repository.MarketRepositoryRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class OrdersRepo(
    private var repositoryRoom: MarketRepositoryRoom,
    private var repositoryNetwork: MarketRepositoryNetwork
) : CoroutineScope {

    private val job = SupervisorJob()

    private val _ordersLiveData: MutableLiveData<List<Order>> = MutableLiveData()
    val ordersLiveData: LiveData<List<Order>> = _ordersLiveData

    private val _ordersErrorLiveData: MutableLiveData<String> = MutableLiveData()
    val ordersErrorLiveData: LiveData<String> = _ordersErrorLiveData

    fun getOrders() {
        launch {
            val response = repositoryNetwork.getOrders(1)

            if (response.isSuccessful) {
                response.body()?.let {
                    _ordersLiveData.postValue(it)
                } ?: run {
                    _ordersErrorLiveData.postValue(response.message())
                }
            } else {
                _ordersErrorLiveData.postValue(response.message())
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}