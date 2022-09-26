package com.example.marketplace

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.marketplace.base.BaseActivity
import com.example.marketplace.databinding.ActivityMainBinding
import com.example.marketplace.utils.ConnectionType
import com.example.marketplace.utils.NetworkMonitorUtil
import com.google.android.material.snackbar.Snackbar

class MainActivity : BaseActivity() {

    private val networkMonitor = NetworkMonitorUtil(this)
    private var mSnackBar: Snackbar? = null

    private lateinit var binding: ActivityMainBinding
    private val mainAppBarConfiguration by lazy {
        AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.mainFragment, R.id.cartFragment, R.id.productFragment
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        networkMonitor.result = { isAvailable, type ->
            runOnUiThread {
                when (isAvailable) {
                    true -> {
                        when (type) {
                            ConnectionType.Wifi -> {
                                showMessage(true)

                                Toast.makeText(
                                    this,
                                    "Wifi Connection",
                                    Toast.LENGTH_SHORT
                                ).show()
                                Log.i("NETWORK_MONITOR_STATUS", "Wifi Connection")
                            }
                            ConnectionType.Cellular -> {
                                showMessage(true)

                                Toast.makeText(
                                    this,
                                    "Cellular Connection",
                                    Toast.LENGTH_SHORT
                                ).show()
                                Log.i("NETWORK_MONITOR_STATUS", "Cellular Connection")
                            }
                            else -> { }
                        }
                    }
                    false -> {
                        showMessage(false)

                        Toast.makeText(
                            this,
                            "No Connection",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.i("NETWORK_MONITOR_STATUS", "No Connection")
                    }
                }
            }
        }
    }

    private fun showMessage(isConnected: Boolean) {
        if (!isConnected) {
            val messageToUser = "You are offline now."
            mSnackBar = Snackbar.make(findViewById(R.id.root_layout), messageToUser, Snackbar.LENGTH_LONG)
            mSnackBar?.duration = Snackbar.LENGTH_INDEFINITE
            mSnackBar?.show()
        } else {
            mSnackBar?.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }

    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.mainActivityNavHostFragment)
        return NavigationUI.navigateUp(
            navController, mainAppBarConfiguration) || super.onSupportNavigateUp()
    }
}