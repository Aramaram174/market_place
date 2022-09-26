package com.example.marketplace.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.marketplace.data.db.model.Order
import com.example.marketplace.data.db.model.Product
import com.example.marketplace.data.db.model.User

@Dao
interface MarketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM user")
    fun getUser(): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProductToFavoriteList(product: Product)

    @Update
    fun updateProductFromFavorite(product: Product)

    @Query("UPDATE products SET isFavorite = 0 WHERE id = :id")
    fun deleteProductFromFavoriteList(id: Int)

    @Query("SELECT * FROM products WHERE isFavorite = 1")
    fun getAllProductsFromFavoriteList(): LiveData<List<Product>>



    @Query("UPDATE products SET isCartItem = 1 WHERE id = :id")
    fun addProductToCart(id: Int)

    @Update
    fun updateProductFromCart(product: Product)

    @Query("DELETE FROM products WHERE id = :id")
    fun deleteProductFromCart(id: Int)

    @Query("SELECT * FROM products WHERE isCartItem = 1")
    fun getAllProductsFromCart(): LiveData<List<Product>>

    @Query("SELECT COUNT() FROM products WHERE isCartItem = 1")
    fun getAllProductsFromCartCount(): LiveData<Int>

    @Query("UPDATE products SET size = size + 1 WHERE id = :id")
    fun incrementProductOnCart(id: Int)

    @Query("UPDATE products SET size = size - 1 WHERE id = :id")
    fun decrementProductOnCart(id: Int)

    @Query("SELECT * FROM `order`")
    fun getOrders(): LiveData<List<Order>>

//    @Query("")
//    fun setOrders(orders: List<Order>)
}