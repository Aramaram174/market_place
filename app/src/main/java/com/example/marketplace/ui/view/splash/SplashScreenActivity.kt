package com.example.marketplace.ui.view.splash

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.marketplace.MainActivity
import com.example.marketplace.R
import com.example.marketplace.base.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class SplashScreenActivity : BaseActivity(), CoroutineScope {

    private val viewModel: SplashViewModel by viewModel()

    companion object {
        const val PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        setupPermissions()
        observeLiveData()
    }

    private fun getPermissionsList(): MutableList<String> {
        val listOfPermissions =
            mutableListOf(
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.SEND_SMS,
                Manifest.permission.READ_SMS,
                Manifest.permission.VIBRATE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_CONTACTS
            )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            listOfPermissions.add(Manifest.permission.REQUEST_DELETE_PACKAGES)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            listOfPermissions.add(Manifest.permission.MANAGE_EXTERNAL_STORAGE)
        }
        return listOfPermissions
    }

    private fun setupPermissions() {
        requestPermissions(getPermissionsList().toTypedArray(), PERMISSION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            val n = grantResults.firstOrNull {
                it != PackageManager.PERMISSION_GRANTED
            }
            launch {
                viewModel.getConfig()
            }
        }
    }

    private fun observeLiveData() {
        viewModel.configLiveData.observe(this,{ configDownloaded ->
            if (configDownloaded) {
                Handler(Looper.getMainLooper()).postDelayed({
                    val i = Intent(this@SplashScreenActivity, MainActivity::class.java)
                    startActivity(i)
                    finish()
                }, 10)
            }
        })
    }

    override val coroutineContext: CoroutineContext = Dispatchers.IO
}