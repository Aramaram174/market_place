package com.example.marketplace.di.module

import com.example.marketplace.ui.view.order.OrdersRepo
import org.koin.dsl.module

val repoModule = module {
    single { OrdersRepo(get(), get()) }
}