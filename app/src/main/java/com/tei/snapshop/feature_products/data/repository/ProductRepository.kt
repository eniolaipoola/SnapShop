package com.tei.snapshop.feature_products.data.repository

import android.content.Context
import com.tei.snapshop.R
import com.tei.snapshop.data.network.APIService
import com.tei.snapshop.data.network.NoInternetException
import com.tei.snapshop.feature_products.data.Product
import com.tei.snapshop.feature_products.data.local.ProductDao
import com.tei.snapshop.ui.theme.isInternetAvailable
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Class Description
 * Created by Eniola Ipoola on 15/08/2024.
 * Copyright (c). All rights reserved
 */

class ProductRepository  @Inject constructor(
    private val productDao: ProductDao,
    private val apiService: APIService,
    @ApplicationContext private val context: Context
) {
    private suspend fun fetchAndCacheProduct(batch: Int, offset:Int): List<Product> {
        if(!isInternetAvailable(context)) {
            // No internet, return or notify the viewModel
            throw NoInternetException(context.getString(R.string.no_internet_connectio))
        } else {
            val productsFromApi = apiService.fetchProducts(batch, offset)
            productDao.insertAllProduct(productsFromApi)
            return productsFromApi
        }
    }

    suspend fun getAllProducts(batch: Int = 50): List<Product> {
        val products = productDao.fetchAllProduct()
        return if(products?.isNotEmpty() == true) {
            products
        } else {
            fetchAndCacheProduct(batch, 0)
        }
    }

    suspend fun loadNextBatch(currentSize: Int, limit: Int = 50): List<Product> {
        return fetchAndCacheProduct(limit, currentSize)
    }

}