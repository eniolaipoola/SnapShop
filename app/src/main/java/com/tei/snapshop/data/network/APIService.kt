package com.tei.snapshop.data.network

import com.tei.snapshop.feature_products.data.Product
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Class Description
 * Created by Eniola Ipoola on 05/06/2024.
 * Copyright (c). All rights reserved
 */
interface APIService {
    @GET("products")
    suspend fun fetchProducts(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): List<Product>
}