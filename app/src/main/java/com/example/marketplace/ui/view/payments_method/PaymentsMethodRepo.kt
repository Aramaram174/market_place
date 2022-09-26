package com.example.marketplace.ui.view.payments_method

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marketplace.data.db.model.Payment
import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.data.repository.MarketRepositoryRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PaymentsMethodRepo(
    private var repositoryRoom: MarketRepositoryRoom,
    private var repositoryNetwork: MarketRepositoryNetwork
) : CoroutineScope {

    private val job = SupervisorJob()

    private val _paymentsMethodLiveData: MutableLiveData<List<Payment>> = MutableLiveData()
    val paymentsMethodLiveData: LiveData<List<Payment>> = _paymentsMethodLiveData

    private val _paymentsMethodErrorLiveData: MutableLiveData<String> = MutableLiveData()
    val paymentsMethodErrorLiveData: LiveData<String> = _paymentsMethodErrorLiveData

    fun getPaymentsMethod() {
        launch {
            val response = repositoryNetwork.getPaymentMethods(1)

            if (response.isSuccessful) {
                response.body()?.let {
                    _paymentsMethodLiveData.postValue(it)
                } ?: run {
                    _paymentsMethodErrorLiveData.postValue(response.message())
                }
            } else {
                _paymentsMethodErrorLiveData.postValue(response.message())
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}