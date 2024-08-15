package com.tei.snapshop.feature_products.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tei.snapshop.feature_products.data.Product

/**
 * Class Description
 * Created by Eniola Ipoola on 15/08/2024.
 * Copyright (c). All rights reserved
 */
@Dao
interface ProductDao {
    @Query("SELECT * FROM products WHERE id = :productId")
    suspend fun getProductById(productId: Int): Product?

    @Query("SELECT * FROM products")
    suspend fun fetchAllProduct(): List<Product>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProduct(product: List<Product>)

    @Query("DELETE FROM products")
    suspend fun clearProducts()
}