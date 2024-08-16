package com.tei.snapshop.feature_cart.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.tei.snapshop.data.local.AppDatabase
import com.tei.snapshop.feature_cart.data.Cart
import com.tei.snapshop.feature_cart.data.CartProduct
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Class Description
 * Created by Eniola Ipoola on 16/08/2024.
 * Copyright (c). All rights reserved
 */

@RunWith(AndroidJUnit4::class)
@SmallTest
class CartDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var cartsDao: CartsDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java).allowMainThreadQueries().build()

        cartsDao = database.cartDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertCart_returnsNotNull() {
        val dateStr = "2024-08-12"
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(dateStr) ?: Date()
        val cart = Cart(1, 2345, date, product = listOf(
            CartProduct(2, 3),
            CartProduct(5, 2)
        ))

    }

}