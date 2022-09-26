package com.example.marketplace.ui.view.my_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.data.db.model.User
import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.data.repository.MarketRepositoryRoom
import com.example.marketplace.ui.view.sign_up.AuthRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MyDetailsViewModel(
    private var repositoryRoom: MarketRepositoryRoom,
    private var repositoryNetwork: MarketRepositoryNetwork
) : ViewModel(), CoroutineScope {

    private val job = SupervisorJob()
    private val authRepo: AuthRepo = AuthRepo(repositoryRoom, repositoryNetwork)

    private lateinit var savedUser: User
    private val _isChangedProfileDataLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var isChangedProfileDataLiveData: LiveData<Boolean> = _isChangedProfileDataLiveData

    fun getUser() {
        viewModelScope.launch {
            authRepo.getUser()
        }
    }

    fun updateUser(token: String, user: User) {
        viewModelScope.launch {
            authRepo.updateUser(token, user)
        }
    }

    fun userLiveData() = authRepo.userLiveData

    fun isChangedProfileData(user: User) {
        savedUser = authRepo.userLiveData.value!!
        _isChangedProfileDataLiveData.value = user.firstName != savedUser.firstName
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

}