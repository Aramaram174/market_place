package com.example.marketplace.ui.view.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marketplace.data.db.model.Address
import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.data.repository.MarketRepositoryRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AddressesRepo(
    private var repositoryRoom: MarketRepositoryRoom,
    private var repositoryNetwork: MarketRepositoryNetwork
) : CoroutineScope {

    private val job = SupervisorJob()

    private val _addressesLiveData: MutableLiveData<List<Address>> = MutableLiveData()
    val addressesLiveData: LiveData<List<Address>> = _addressesLiveData

    private val _addressesErrorLiveData: MutableLiveData<String> = MutableLiveData()
    val addressesErrorLiveData: LiveData<String> = _addressesErrorLiveData

    fun getAddresses() {
        launch {
            val response = repositoryNetwork.getAddresses(1)

            if (response.isSuccessful) {
                response.body()?.let {
                    _addressesLiveData.postValue(it)
                } ?: run {
                    _addressesErrorLiveData.postValue(response.message())
                }
            } else {
                _addressesErrorLiveData.postValue(response.message())
            }
        }
    }

    fun addAddress(address: Address) {
        launch {
            val response = repositoryNetwork.addAddresses(address)

            if (response.isSuccessful) {
                response.body()?.let {
//                    _addressesLiveData.postValue(it)
                } ?: run {
                    _addressesErrorLiveData.postValue(response.message())
                }
            } else {
                _addressesErrorLiveData.postValue(response.message())
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}