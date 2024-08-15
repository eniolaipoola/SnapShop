package com.tei.snapshop.feature_products.data.repository

import com.tei.snapshop.data.local.AppDatabase
import com.tei.snapshop.data.network.APIService
import com.tei.snapshop.feature_products.data.Product
import com.tei.snapshop.feature_products.data.local.ProductDao
import javax.inject.Inject

/**
 * Class Description
 * Created by Eniola Ipoola on 15/08/2024.
 * Copyright (c). All rights reserved
 */

class ProductRepository  @Inject constructor(
    private val productDao: ProductDao,
    private val appDatabase: AppDatabase,
    private val apiService: APIService
) {
    suspend fun fetchAndCacheProduct(): List<Product> {
        val products = apiService.fetchProducts()
        productDao.insertAllProduct(products)
        return products
    }

    suspend fun getAllProducts(): List<Product> {
        val products = productDao.fetchAllProduct()
        return if(products?.isNotEmpty() == true) {
            products
        } else {
            fetchAndCacheProduct()
        }
    }
}