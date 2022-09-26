package com.example.marketplace.ui.view.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.data.repository.MarketRepositoryRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CartViewModel(private var repositoryRoom: MarketRepositoryRoom,
                    private var repositoryNetwork: MarketRepositoryNetwork
) : ViewModel(),
    CoroutineScope {

    private val job = SupervisorJob()
    private val cartRepo: CartRepo = CartRepo(repositoryRoom, repositoryNetwork)

    fun getCart(token: String, limit: String, offset: String) {
        viewModelScope.launch {
            cartRepo.getCart(token, limit, offset)
        }
    }

    fun addProductToCart(token: String, id: Int) {
        viewModelScope.launch {
            cartRepo.addProductToCart(token, id)
        }
    }

    fun deleteProductFromCart(token: String, id: String) {
        viewModelScope.launch {
            cartRepo.deleteProductFromCart(token, id)
        }
    }

    fun cartLiveData() = cartRepo.cartLiveData

    fun deleteCartItemLiveData() = cartRepo.deleteCartItemLiveData

    fun errorLiveData() = cartRepo.errorLiveData

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    override val coroutineContext: CoroutineContext = job + Dispatchers.IO
}