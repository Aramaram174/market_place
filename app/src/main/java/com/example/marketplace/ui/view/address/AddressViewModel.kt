package com.example.marketplace.ui.view.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.data.db.model.Address
import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.data.repository.MarketRepositoryRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AddressViewModel(
    private var repositoryRoom: MarketRepositoryRoom,
    private var repositoryNetwork: MarketRepositoryNetwork
) : ViewModel(), CoroutineScope {

    private val addressesRepo: AddressesRepo = AddressesRepo(repositoryRoom, repositoryNetwork)
    private val job = SupervisorJob()

    init {
        getAddresses()
    }

    private fun getAddresses() {
        viewModelScope.launch {
            addressesRepo.getAddresses()
        }
    }

    fun addAddress(address: Address) {
        viewModelScope.launch {
            addressesRepo.addAddress(address)
        }
    }

    fun addressesLiveData() = addressesRepo.addressesLiveData

    fun addressesErrorLiveData() = addressesRepo.addressesErrorLiveData

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    override val coroutineContext: CoroutineContext = job + Dispatchers.IO
}