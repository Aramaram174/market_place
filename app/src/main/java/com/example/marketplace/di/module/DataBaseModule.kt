package com.example.marketplace.di.module

import android.app.Application
import androidx.room.Room
import com.example.marketplace.data.db.AppDatabase
import com.example.marketplace.data.db.MarketDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}

fun provideDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, "market_database")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()
}

fun provideDao(database: AppDatabase): MarketDao {
    return database.marketDao()
}