package com.tei.snapshop.feature_cart.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tei.snapshop.feature_cart.data.Cart
import com.tei.snapshop.feature_cart.data.CartProduct
import kotlinx.coroutines.flow.Flow

/**
 * Class Description
 * Created by Eniola Ipoola on 16/08/2024.
 * Copyright (c). All rights reserved
 */

@Dao
interface CartsDao {

    // a user can only have a cart per time but with different
    @Query("SELECT * FROM carts")
    fun getCart(): Flow<Cart>?
    @Insert
    suspend fun insertCart(data: Cart)

    @Query("Select * FROM carts where userId = :userId")
    suspend fun getUserCart(userId: Int): Cart?

    @Query("Update carts SET product = :products WHERE userId = :userId")
    suspend fun updateProductInCart(userId: Int, products: List<CartProduct>)

    @Update
    suspend fun updateCart(cart: Cart)

    @Query("DELETE FROM carts where id = :cartId")
    suspend fun deleteCart(cartId: Int)
}