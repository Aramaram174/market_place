package com.example.marketplace.data.api.requests

import com.example.marketplace.data.db.model.*
import com.example.marketplace.ui.view.sort.SortType
import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("config")
    suspend fun getConfig(): Response<JsonObject>

    @POST("/users/login/")
    suspend fun signIn(@Body signInBody: JsonObject): Response<User>

    @POST("/users/sign-up/")
    suspend fun signUp(@Body signUpBody: JsonObject): Response<User>

    @POST("/users/logout/")
    suspend fun logOut(@Header("Authorization") token: String): Response<JsonObject>

    @POST("/users/confirm-account/")
    suspend fun confirmAccount(@Body confirmAccountBody: JsonObject): Response<JsonObject>

    @POST("/users/forgot-password/")
    suspend fun forgotPassword(@Body forgotPasswordBody: JsonObject): Response<JsonObject>

    @POST("/users/check-token/")
    suspend fun checkResetKey(@Body resetKeyBody: JsonObject): Response<JsonObject>

    @POST("/users/change-password/")
    suspend fun changePassword(@Header("Authorization") token: String,
                                   @Body changePasswordBody: JsonObject): Response<JsonObject>

    @POST("/users/resend-email/")
    suspend fun resendEmail(@Body resendEmailBody: JsonObject): Response<JsonObject>

    @POST("/users/reset-password/{reset_key}/")
    suspend fun resetPassword(@Path("reset_key") resetKey: String, @Body resetPasswordBody: JsonObject): Response<JsonObject>

    @GET("get_user_data")
    suspend fun getUserData(): Response<User>

    @PUT("/users/update_profile/")
    suspend fun updateProfile(@Header("Authorization") token: String, @Body user: User): Response<JsonObject>

    //    @GET("get_catalog")
    @GET("categories")
    suspend fun getCatalog(): Response<List<Category>>

//    @GET("get_products_by_category")
//    @GET("products/{id}/")
//    suspend fun getProductsByCategory(@Path("id") id: String): Response<ProductResponse>

    @GET("products/{id}/")
    suspend fun getProductsByCategory(@Path("id") id: String, @Query("limit") limit: String, @Query("offset") offset: String): Response<ProductResponse>

    @GET("products/{id}/")
    suspend fun getSortedProductsByCategory(@Path("id") id: String, @Query("sort") sortType: SortType, @Query("limit") limit: String, @Query("offset") offset: String): Response<ProductResponse>

    @GET("search-product/{query}/")
    suspend fun getSearchedProduct(@Query("search") query: String, @Query("limit") limit: String, @Query("offset") offset: String): Response<ProductResponse>

    @GET("/orders/")
    suspend fun getOrders(): Response<List<Order>>

    @GET("/returns/")
    suspend fun getReturns(): Response<List<Return>>

    @GET("/cart/")
    suspend fun getCart(@Header("Authorization") token: String): Response<ProductResponse>

    @POST("/cart/{id}")
    suspend fun addProductToCart(@Header("Authorization") token: String, @Path("id") id: Int): Response<JsonObject>

    @DELETE("/cart_item/{id}/")
    suspend fun deleteProductFromCart(@Header("Authorization") token: String, @Path("id") id: String): Response<JsonObject>

    @GET("/favorite-product/")
    suspend fun getFavorites(@Header("Authorization") token: String, @Query("limit") limit: String, @Query("offset") offset: String): Response<ProductResponse>

    @POST("/favorite-product/")
    suspend fun addProductToFavorites(@Header("Authorization") token: String, @Body productIdBody: JsonObject): Response<JsonObject>

    @DELETE("/favorite-product/{id}/")
    suspend fun deleteProductFromFavorites(@Header("Authorization") token: String, @Path("id") id: Int): Response<JsonObject>

    @GET("/addresses/")
    suspend fun getAddresses(): Response<List<Address>>

    @POST("/address/")
    suspend fun addAddress(address: Address): Response<Boolean>

    @PUT("/address/{id}/")
    suspend fun editAddress(id: Int)

    @DELETE("/address/{id}/")
    suspend fun deleteAddress(id: Int)

    @GET("get_payment_methods")
    suspend fun getPaymentMethods(): Response<List<Payment>>

    @GET("get_notifications")
    suspend fun getNotifications(): Response<List<Notification>>
}