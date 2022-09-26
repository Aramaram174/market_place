package com.example.marketplace.ui.view.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.utils.config.ConfigParser
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class SplashViewModel(private var repositoryNetwork: MarketRepositoryNetwork) : ViewModel(), CoroutineScope {

    private var requestErrorCount = 0
    private val _configLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val configLiveData: LiveData<Boolean> = _configLiveData

    companion object{
        private val TAG = SplashViewModel::class.java.simpleName
    }

    fun getConfig() {
        viewModelScope.launch {
            val response = repositoryNetwork.getConfig(1)
            checkResponse(response)
        }
    }

    private fun checkResponse(response: Response<JsonObject>){
        if (response.isSuccessful) {
            response.body()?.let {jsonObject ->
                ConfigParser.ourInstance.setResponse(response = jsonObject)
                _configLiveData.value = true
            } ?: run {
                Log.e(TAG, "Config response body is null")
            }
        } else {
            if (requestErrorCount < 2) {
                getConfig()
                requestErrorCount++
            } else {
                Log.e(TAG, "Error download config")
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
}