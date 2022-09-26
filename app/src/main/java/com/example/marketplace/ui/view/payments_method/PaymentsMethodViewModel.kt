package com.example.marketplace.ui.view.payments_method

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.data.repository.MarketRepositoryRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PaymentsMethodViewModel(
    private var repositoryRoom: MarketRepositoryRoom,
    private var repositoryNetwork: MarketRepositoryNetwork
) : ViewModel(), CoroutineScope {

    private val job = SupervisorJob()
    private val paymentsMethodRepo: PaymentsMethodRepo =
        PaymentsMethodRepo(repositoryRoom, repositoryNetwork)

    init {
        getPaymentsMethod()
    }

    private fun getPaymentsMethod() {
        viewModelScope.launch {
            paymentsMethodRepo.getPaymentsMethod()
        }
    }

    fun paymentsMethodLiveData() = paymentsMethodRepo.paymentsMethodLiveData

    fun paymentsMethodErrorLiveData() = paymentsMethodRepo.paymentsMethodErrorLiveData

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    override val coroutineContext: CoroutineContext = job + Dispatchers.IO
}