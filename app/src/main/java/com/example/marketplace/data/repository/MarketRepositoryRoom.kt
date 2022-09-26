package com.example.marketplace.data.repository

import androidx.lifecycle.LiveData
import com.example.marketplace.data.db.MarketDao
import com.example.marketplace.data.db.model.Order
import com.example.marketplace.data.db.model.Product
import com.example.marketplace.data.db.model.Category
import com.example.marketplace.data.db.model.User

interface MarketRepositoryRoom {
    fun addUser(user: User)
    fun updateUser(user: User)
    fun deleteUser(user: User)
    fun getUser(): User

    fun addProductToFavoriteList(product: Product)
    fun deleteProductFromFavoriteList(id: Int)
    fun getAllProductsFromFavoriteList(): LiveData<List<Product>>

    fun addProductToCart(id: Int)
    fun deleteProductFromCart(id: Int)
    fun getAllProductsFromCart(): LiveData<List<Product>>
    fun getAllProductsFromCartCount(): LiveData<Int>

    fun getOrders(): LiveData<List<Order>>
    fun setOrders(orders: List<Order>)

    fun setCatalog(categories: List<Category>)
}

class MarketRepositoryRoomImpl(private val marketDao: MarketDao) : MarketRepositoryRoom {
    override fun addUser(user: User) = marketDao.addUser(user)
    override fun updateUser(user: User) = marketDao.updateUser(user)
    override fun deleteUser(user: User) = marketDao.deleteUser(user)
    override fun getUser(): User = marketDao.getUser()

    override fun addProductToFavoriteList(product: Product) = marketDao.addProductToFavoriteList(product)
    override fun deleteProductFromFavoriteList(id: Int) = marketDao.deleteProductFromFavoriteList(id)
    override fun getAllProductsFromFavoriteList(): LiveData<List<Product>> = marketDao.getAllProductsFromFavoriteList()

    override fun addProductToCart(id: Int) = marketDao.addProductToCart(id)
    override fun deleteProductFromCart(id: Int) = marketDao.deleteProductFromCart(id)
    override fun getAllProductsFromCart(): LiveData<List<Product>> = marketDao.getAllProductsFromCart()
    override fun getAllProductsFromCartCount(): LiveData<Int> = marketDao.getAllProductsFromCartCount()

    override fun getOrders(): LiveData<List<Order>> = marketDao.getOrders()
    override fun setOrders(orders: List<Order>) = print("")

    override fun setCatalog(categories: List<Category>) = print("")
}