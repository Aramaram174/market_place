package com.example.marketplace.ui.view.sign_in

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

class SignInViewModel(
    private var repositoryRoom: MarketRepositoryRoom,
    private var repositoryNetwork: MarketRepositoryNetwork
) : ViewModel(), CoroutineScope {

    private val job = SupervisorJob()
    private val authRepo: AuthRepo = AuthRepo(repositoryRoom, repositoryNetwork)

    fun signIn(login: String, password: String) {
        viewModelScope.launch {
            authRepo.signIn(login, password)
        }
    }

    fun userLiveData() = authRepo.userLiveData

    fun errorLiveData() = authRepo.errorLiveData

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    override val coroutineContext: CoroutineContext = job + Dispatchers.IO
}