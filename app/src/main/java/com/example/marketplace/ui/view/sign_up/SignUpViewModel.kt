package com.example.marketplace.ui.view.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.data.repository.MarketRepositoryRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SignUpViewModel(
    private var repositoryRoom: MarketRepositoryRoom,
    private var repositoryNetwork: MarketRepositoryNetwork
) : ViewModel(), CoroutineScope {

    private val job = SupervisorJob()
    private val authRepo: AuthRepo = AuthRepo(repositoryRoom, repositoryNetwork)

    fun signUp(firstName: String, lastName: String, email: String, password: String, repeatPassword: String) {
        viewModelScope.launch {
            authRepo.signUp(firstName, lastName, email, password, repeatPassword)
        }
    }

    fun userLiveData() = authRepo.userLiveData

    fun userErrorLiveData() = authRepo.errorLiveData

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    override val coroutineContext: CoroutineContext = job + Dispatchers.IO
}