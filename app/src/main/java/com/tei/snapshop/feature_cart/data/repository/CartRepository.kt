package com.tei.snapshop.feature_cart.data.repository

import android.content.Context
import com.tei.snapshop.data.network.APIService
import com.tei.snapshop.feature_cart.data.local.CartsDao
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Class Description
 * Created by Eniola Ipoola on 16/08/2024.
 * Copyright (c). All rights reserved
 */
class CartRepository @Inject constructor(
    private val cartsDao: CartsDao,
    private val apiService: APIService,
    @ApplicationContext private val context: Context
) {

    suspend fun addProductToCart() {

    }
}