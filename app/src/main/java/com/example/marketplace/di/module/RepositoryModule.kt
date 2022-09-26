package com.example.marketplace.di.module

import com.example.marketplace.data.network.MarketRepositoryNetwork
import com.example.marketplace.data.network.MarketRepositoryNetworkImpl
import com.example.marketplace.data.repository.MarketRepositoryRoom
import com.example.marketplace.data.repository.MarketRepositoryRoomImpl
import org.koin.dsl.module

val repositoryModule = module {

    factory<MarketRepositoryRoom> {
        MarketRepositoryRoomImpl(get())
    }
    factory<MarketRepositoryNetwork> {
        MarketRepositoryNetworkImpl(get())
    }
}