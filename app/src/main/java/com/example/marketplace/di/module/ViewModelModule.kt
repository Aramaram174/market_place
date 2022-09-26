package com.example.marketplace.di.module

import com.example.marketplace.ui.view.account.AccountViewModel
import com.example.marketplace.ui.view.address.AddressViewModel
import com.example.marketplace.ui.view.cart.CartViewModel
import com.example.marketplace.ui.view.catalog.CatalogViewModel
import com.example.marketplace.ui.view.change_password.ChangePasswordViewModel
import com.example.marketplace.ui.view.checkout.CheckoutViewModel
import com.example.marketplace.ui.view.favorite.FavoriteViewModel
import com.example.marketplace.ui.view.filter.FilterSharedViewModel
import com.example.marketplace.ui.view.filter.multiselect.MultiselectFilterViewModel
import com.example.marketplace.ui.view.filter.price.PriceViewModel
import com.example.marketplace.ui.view.reset_password.ResetPasswordViewModel
import com.example.marketplace.ui.view.home.HomeViewModel
import com.example.marketplace.ui.view.inner_catalog.InnerCatalogViewModel
import com.example.marketplace.ui.view.main.MainViewModel
import com.example.marketplace.ui.view.my_details.MyDetailsViewModel
import com.example.marketplace.ui.view.order.OrdersViewModel
import com.example.marketplace.ui.view.payments_method.PaymentsMethodViewModel
import com.example.marketplace.ui.view.product.ProductViewModel
import com.example.marketplace.ui.view.sign_up.SignUpViewModel
import com.example.marketplace.ui.view.search.SearchViewModel
import com.example.marketplace.ui.view.settings.SettingsViewModel
import com.example.marketplace.ui.view.sign_in.SignInViewModel
import com.example.marketplace.ui.view.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { MainViewModel() }
    viewModel { FavoriteViewModel() }
    viewModel { CartViewModel(get(), get()) }
    viewModel { ProductViewModel(get()) }
    viewModel { HomeViewModel() }
    viewModel { CatalogViewModel(get(), get()) }
    viewModel { InnerCatalogViewModel(get(), get()) }
    viewModel { OrdersViewModel(get(), get()) }
    viewModel { SearchViewModel(get(), get()) }
    viewModel { AddressViewModel(get(), get()) }
    viewModel { PaymentsMethodViewModel(get(), get()) }
    viewModel { SignInViewModel(get(), get()) }
    viewModel { SignUpViewModel(get(), get()) }
    viewModel { ResetPasswordViewModel(get(), get()) }
    viewModel { ChangePasswordViewModel(get(), get()) }
    viewModel { MyDetailsViewModel(get(), get()) }
    viewModel { AccountViewModel(get(), get()) }
    viewModel { CheckoutViewModel(get(), get()) }
    viewModel { SettingsViewModel(get(), get()) }
    viewModel { PriceViewModel() }
    viewModel { FilterSharedViewModel() }
    viewModel { MultiselectFilterViewModel() }
}