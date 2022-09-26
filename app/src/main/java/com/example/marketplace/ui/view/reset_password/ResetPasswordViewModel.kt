package com.example.marketplace.ui.view.reset_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.data.repository.MarketRepositoryRoom
import com.example.marketplace.ui.view.sign_up.AuthRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ResetPasswordViewModel(
    private var repositoryRoom: MarketRepositoryRoom,
    private var repositoryNetwork: MarketRepositoryNetwork) : ViewModel(), CoroutineScope {

    private val job = SupervisorJob()
    private val authRepo: AuthRepo = AuthRepo(repositoryRoom, repositoryNetwork)

    fun forgotPassword(email: String) {
        viewModelScope.launch {
            authRepo.forgotPassword(email)
        }
    }

    fun checkResetKey(resetKey: String) {
        viewModelScope.launch {
            authRepo.checkResetKey(resetKey)
        }
    }

    fun forgotPasswordLiveData() = authRepo.forgotPasswordLiveData

    fun pinLiveData() = authRepo.pinLiveData

    fun forgotPasswordErrorLiveData() = authRepo.errorLiveData

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    override val coroutineContext: CoroutineContext = job + Dispatchers.IO
}