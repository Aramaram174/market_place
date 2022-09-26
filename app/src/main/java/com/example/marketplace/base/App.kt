package com.example.marketplace.base

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.example.marketplace.di.module.*
import com.example.marketplace.utils.LocaleManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(databaseModule, repositoryModule, viewModelModule, appModule, repoModule, sharedPreferencesModule))
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.setLocale(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.setLocale(this)
    }
}