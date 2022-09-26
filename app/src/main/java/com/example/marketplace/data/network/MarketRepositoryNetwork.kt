package com.example.marketplace.data.network

import com.example.marketplace.data.api.requests.ApiService
import com.example.marketplace.data.db.model.*
import com.example.marketplace.ui.view.sort.SortType
import com.google.gson.JsonObject
import retrofit2.Response

interface MarketRepositoryNetwork {

    suspend fun getConfig(siteId: Int): Response<JsonObject>

    suspend fun signIn(siteId: Int, signInBody: JsonObject): Response<User>

    suspend fun signUp(siteId: Int, signUpBody: JsonObject): Response<User>

    suspend fun logOut(token: String): Response<JsonObject>

    suspend fun confirmAccount(siteId: Int, confirmAccountBody: JsonObject): Response<JsonObject>

    suspend fun forgotPassword(siteId: Int, forgotPasswordBody: JsonObject): Response<JsonObject>

    suspend fun checkResetKey(resetKeyBody: JsonObject): Response<JsonObject>

    suspend fun changePassword(token: String, changePasswordBody: JsonObject): Response<JsonObject>

    suspend fun resendEmail(siteId: Int, resendEmailBody: JsonObject): Response<JsonObject>

    suspend fun resetPassword(resetKey: String, resetPasswordBody: JsonObject): Response<JsonObject>

    suspend fun getUserData(siteId: Int): Response<User>

    suspend fun updateProfile(token: String, user: User): Response<JsonObject>

    suspend fun getCatalog(siteId: Int): Response<List<Category>>

    suspend fun getProductsByCategory(siteId: Int, categoryId: String, limit: String, offset: String): Response<ProductResponse>

    suspend fun getSortedProductsByCategory(siteId: Int, categoryId: String, sortType: SortType, limit: String, offset: String): Response<ProductResponse>

    suspend fun getSearchedProduct(siteId: Int, query: String, limit: String, offset: String): Response<ProductResponse>

    suspend fun getOrders(siteId: Int): Response<List<Order>>

    suspend fun getReturns(siteId: Int): Response<List<Return>>

    suspend fun getCart(token: String, limit: String, offset: String): Response<ProductResponse>

    suspend fun addProductToCart(token: String, id: Int): Response<JsonObject>

    suspend fun deleteProductFromCart(token: String, id: String): Response<JsonObject>

    suspend fun getFavorites(token: String, limit: String, offset: String): Response<ProductResponse>

    suspend fun addProductToFavorites(token: String, productIdBody: JsonObject): Response<JsonObject>

    suspend fun deleteProductFromFavorites(token: String, id: Int): Response<JsonObject>

    suspend fun getAddresses(siteId: Int): Response<List<Address>>

    suspend fun addAddresses(address: Address): Response<Boolean>

    suspend fun getPaymentMethods(siteId: Int): Response<List<Payment>>

    suspend fun getNotifications(siteId: Int): Response<List<Notification>>

    suspend fun deleteProductFromFavoriteList(id: Int)
}

class MarketRepositoryNetworkImpl(private val apiService: ApiService) : MarketRepositoryNetwork {

    override suspend fun getConfig(siteId: Int): Response<JsonObject> = apiService.getConfig()

    override suspend fun signIn(siteId: Int, signInBody: JsonObject): Response<User> = apiService.signIn(signInBody)

    override suspend fun signUp(siteId: Int, signUpBody: JsonObject): Response<User> = apiService.signUp(signUpBody)

    override suspend fun logOut(token: String): Response<JsonObject> = apiService.logOut("Token $token")

    override suspend fun confirmAccount(siteId: Int, confirmAccountBody: JsonObject): Response<JsonObject> = apiService.confirmAccount(confirmAccountBody)

    override suspend fun forgotPassword(siteId: Int, forgotPasswordBody: JsonObject): Response<JsonObject> = apiService.forgotPassword(forgotPasswordBody)

    override suspend fun checkResetKey(resetKeyBody: JsonObject): Response<JsonObject> = apiService.checkResetKey(resetKeyBody)

    override suspend fun changePassword(token: String, changePasswordBody: JsonObject): Response<JsonObject> = apiService.changePassword("Token $token", changePasswordBody)

    override suspend fun resendEmail(siteId: Int, resendEmailBody: JsonObject): Response<JsonObject> = apiService.resendEmail(resendEmailBody)

    override suspend fun resetPassword(resetKey: String, resetPasswordBody: JsonObject): Response<JsonObject> = apiService.resetPassword(resetKey, resetPasswordBody)

    override suspend fun getUserData(siteId: Int): Response<User> = apiService.getUserData()

    override suspend fun updateProfile(token: String, user: User): Response<JsonObject> = apiService.updateProfile(token, user)

    override suspend fun getCatalog(siteId: Int): Response<List<Category>> = apiService.getCatalog()

    override suspend fun getProductsByCategory(siteId: Int, categoryId: String, limit: String, offset: String): Response<ProductResponse> = apiService.getProductsByCategory(categoryId, limit, offset)

    override suspend fun getSortedProductsByCategory(siteId: Int, categoryId: String, sortType: SortType, limit: String, offset: String): Response<ProductResponse> = apiService.getSortedProductsByCategory(categoryId, sortType, limit, offset)

    override suspend fun getSearchedProduct(siteId: Int, query: String, limit: String, offset: String): Response<ProductResponse> = apiService.getSearchedProduct(query, limit, offset)

    override suspend fun getOrders(siteId: Int): Response<List<Order>> = apiService.getOrders()

    override suspend fun getReturns(siteId: Int): Response<List<Return>> = apiService.getReturns()

    override suspend fun getCart(token: String, limit: String, offset: String): Response<ProductResponse> = apiService.getCart("Token $token")

    override suspend fun addProductToCart(token: String, id: Int): Response<JsonObject> = apiService.addProductToCart("Token $token", id)

    override suspend fun deleteProductFromCart(token: String, id: String): Response<JsonObject> = apiService.deleteProductFromCart(token, id)

    override suspend fun getFavorites(token: String, limit: String, offset: String): Response<ProductResponse> = apiService.getFavorites("Token $token", limit, offset)

    override suspend fun addProductToFavorites(token: String, productIdBody: JsonObject): Response<JsonObject> = apiService.addProductToFavorites("Token $token", productIdBody)

    override suspend fun deleteProductFromFavorites(token: String, id: Int): Response<JsonObject> = apiService.deleteProductFromFavorites("Token $token", id)

    override suspend fun getAddresses(siteId: Int): Response<List<Address>> = apiService.getAddresses()

    override suspend fun addAddresses(address: Address): Response<Boolean> = apiService.addAddress(address)

    override suspend fun getPaymentMethods(siteId: Int): Response<List<Payment>> = apiService.getPaymentMethods()

    override suspend fun getNotifications(siteId: Int): Response<List<Notification>>  = apiService.getNotifications()

    override suspend fun deleteProductFromFavoriteList(id: Int) = print(id)
}