package com.tei.snapshop.data.network

import com.tei.snapshop.feature_products.data.Product
import retrofit2.http.GET

/**
 * Class Description
 * Created by Eniola Ipoola on 05/06/2024.
 * Copyright (c). All rights reserved
 */
interface APIService {
    @GET("products")
    suspend fun fetchProducts(): List<Product>
}