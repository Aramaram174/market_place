package com.example.marketplace.ui.view.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marketplace.data.db.model.User
import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.data.repository.MarketRepositoryRoom
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AuthRepo(
    private var repositoryRoom: MarketRepositoryRoom,
    private var repositoryNetwork: MarketRepositoryNetwork
) : CoroutineScope {

    private val job = SupervisorJob()

    private val _userLiveData: MutableLiveData<User> = MutableLiveData()
    val userLiveData: LiveData<User> = _userLiveData

    private val _forgotPasswordLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val forgotPasswordLiveData: LiveData<Boolean> = _forgotPasswordLiveData

    private val _pinLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val pinLiveData: LiveData<Boolean> = _pinLiveData

    private val _changePasswordLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val changePasswordLiveData: LiveData<Boolean> = _changePasswordLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun signIn(login: String, password: String) {
        launch {
            val signInBody = JsonObject()
            signInBody.addProperty("email", login)
            signInBody.addProperty("password", password)

            val response = repositoryNetwork.signIn(1, signInBody)

            if (response.isSuccessful) {
                response.body()?.let {
                    repositoryRoom.addUser(it)
                    _userLiveData.postValue(it)
                } ?: run {
                    _errorLiveData.postValue(response.message())
                }
            } else {
                _errorLiveData.postValue(response.message())
            }
        }
    }

    fun signUp(firstName: String, lastName: String, email: String, password: String, repeatPassword: String) {
        launch {
            val signUpBody = JsonObject()
            signUpBody.addProperty("first_name", firstName)
            signUpBody.addProperty("last_name", lastName)
            signUpBody.addProperty("email", email)
            signUpBody.addProperty("password", password)
            signUpBody.addProperty("repeat_password", repeatPassword)
            signUpBody.addProperty("is_mobile", true)

            val response = repositoryNetwork.signUp(1, signUpBody)

            if (response.isSuccessful) {
                response.body()?.let {
                    repositoryRoom.addUser(it)
                    _userLiveData.postValue(it)
                } ?: run {
                    _errorLiveData.postValue(response.message())
                }
            } else {
                _errorLiveData.postValue(response.message())
            }
        }
    }

    fun confirmAccount(user: User) {
        launch {
            val confirmAccountBody = JsonObject()
            confirmAccountBody.addProperty("token", user.token)

            val response = repositoryNetwork.confirmAccount(1, confirmAccountBody)

            if (response.isSuccessful) {
                response.body()?.let {
//                    _userLiveData.postValue(it)
                } ?: run {
                    _errorLiveData.postValue(response.message())
                }
            } else {
                _errorLiveData.postValue(response.message())
            }
        }
    }

    fun forgotPassword(email: String) {
        launch {
            val forgotPasswordBody = JsonObject()
            forgotPasswordBody.addProperty("email", email)
            forgotPasswordBody.addProperty("is_mobile", true)

            val response = repositoryNetwork.forgotPassword(1, forgotPasswordBody)

            if (response.isSuccessful) {
                response.body()?.let {
                    if (it.get("result").asString.equals("success")){
                        _forgotPasswordLiveData.postValue(true)
                    }else{
                        _errorLiveData.postValue(response.message())
                    }
                } ?: run {
                    _errorLiveData.postValue(response.message())
                }
            } else {
                _errorLiveData.postValue(response.message())
            }
        }
    }

    fun checkResetKey(resetKey: String) {
        launch {
            val resetKeyBody = JsonObject()
            resetKeyBody.addProperty("token", resetKey)
            val response = repositoryNetwork.checkResetKey(resetKeyBody)

            if (response.isSuccessful) {
                response.body()?.let {
                    if (it.get("result").asString.equals("success")){
                        _pinLiveData.postValue(true)
                    }else{
                        _errorLiveData.postValue(response.message())
                    }
                } ?: run {
                    _errorLiveData.postValue(response.message())
                }
            } else {
                _errorLiveData.postValue(response.message())
            }
        }
    }

    fun resetPassword(resetKey: String, newPassword: String, repeatNewPassword: String) {
        val resetPasswordBody = JsonObject()
        resetPasswordBody.addProperty("password", newPassword)
        resetPasswordBody.addProperty("repeat_password", repeatNewPassword)

        launch {
            val response = repositoryNetwork.resetPassword(resetKey, resetPasswordBody)

            if (response.isSuccessful) {
                response.body()?.let {
//                    _ordersLiveData.postValue(it)
                } ?: run {
                    _errorLiveData.postValue(response.message())
                }
            } else {
                _errorLiveData.postValue(response.message())
            }
        }
    }

    fun changePassword(token: String, oldPassword: String, newPassword: String, newRepeatPassword: String) {
        launch {
            val changePasswordBody = JsonObject()
            changePasswordBody.addProperty("old_password", oldPassword)
            changePasswordBody.addProperty("password", newPassword)
            changePasswordBody.addProperty("repeat_password", newRepeatPassword)

            val response = repositoryNetwork.changePassword(token, changePasswordBody)

            if (response.isSuccessful) {
                response.body()?.let {
                    if (it.get("result").asString.equals("success")){
                        _changePasswordLiveData.postValue(true)
                    }else{
                        _errorLiveData.postValue(response.message())
                    }
                } ?: run {
                    _errorLiveData.postValue(response.message())
                }
            } else {
                _errorLiveData.postValue(response.message())
            }
        }
    }

    fun resendEmail(login: String) {
        val resendEmailBody = JsonObject()
        resendEmailBody.addProperty("email", login)
        launch {
            val response = repositoryNetwork.resendEmail(1, resendEmailBody)

            if (response.isSuccessful) {
                response.body()?.let {
//                    _ordersLiveData.postValue(it)
                } ?: run {
                    _errorLiveData.postValue(response.message())
                }
            } else {
                _errorLiveData.postValue(response.message())
            }
        }
    }

    fun logOut(token: String, user: User) {
        launch {
            val response = repositoryNetwork.logOut(token)
//
            if (response.isSuccessful) {
                response.body()?.let {
                    repositoryRoom.deleteUser(user)
                } ?: run {
                    _errorLiveData.postValue(response.message())
                }
            } else {
                _errorLiveData.postValue(response.message())
            }
        }
    }

    fun getUser() {
        launch {
            _userLiveData.postValue(repositoryRoom.getUser())
        }
    }

    fun updateUser(token: String, user: User) {
        launch {
            val response = repositoryNetwork.updateProfile(token, user)
//
            if (response.isSuccessful) {
                response.body()?.let {
                    repositoryRoom.updateUser(user)
                } ?: run {
                    _errorLiveData.postValue(response.message())
                }
            } else {
                _errorLiveData.postValue(response.message())
            }
        }
    }

    override val coroutineContext: CoroutineContext = job + Dispatchers.IO
}