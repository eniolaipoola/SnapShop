package com.tei.snapshop.feature_cart.data.local

import androidx.room.Dao
import androidx.room.Query
import com.tei.snapshop.feature_cart.data.Cart
import com.tei.snapshop.feature_cart.data.CartProduct

/**
 * Class Description
 * Created by Eniola Ipoola on 16/08/2024.
 * Copyright (c). All rights reserved
 */

@Dao
interface CartDao {

    @Query("SELECT * FROM carts")
    suspend fun getCart(): Cart?    // a user can only have a cart per time

    @Query("Select * FROM carts where userId = :userId")
    suspend fun getUserCart(userId: Int): Cart?

    @Query("Update carts SET product = :products WHERE userId = :userId")
    suspend fun updateProductInCart(userId: Int, products: List<CartProduct>)

    @Query("DELETE FROM carts where id = :cartId")
    suspend fun deleteCart(cartId: Int)
}