package com.tei.snapshop.feature_cart.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.tei.snapshop.data.local.AppDatabase
import com.tei.snapshop.feature_cart.data.Cart
import com.tei.snapshop.feature_cart.data.CartProduct
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.CountDownLatch

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
    fun insertCart_returnsNotNull() = runTest {
        val dateStr = "2024-08-12"
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(dateStr) ?: Date()
        val cart = Cart(
            1, 2345, date, product = listOf(
                CartProduct(2, 3),
                CartProduct(5, 2)
            )
        )

        cartsDao.insertCart(cart)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            cartsDao.getCart()?.collect {
                assertThat(it.userId == 2345)
                latch.countDown()
            }
        }

        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun deleteCart_Test() = runTest {
        val dateStr = "2024-08-12"
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(dateStr) ?: Date()
        val cart = Cart(
            1, 2345, date, product = listOf(
                CartProduct(2, 3),
                CartProduct(5, 2)
            )
        )
        val cart2 = Cart(
            2, 2346, date, product = listOf(
                CartProduct(4, 1),
                CartProduct(6, 2)
            )
        )
        cartsDao.insertCart(cart)
        cartsDao.insertCart(cart2)
        cartsDao.deleteCart(1)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            cartsDao.getCart()?.collect {
                assertTrue(it.id != 1)
                assertTrue(it.id == 2)
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()

    }

    @Test
    fun updateCart_returnsTrue() = runTest {
        val dateStr = "2024-08-12"
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(dateStr) ?: Date()
        val cart = Cart(
            1, 2345, date, product = listOf(
                CartProduct(2, 3),
                CartProduct(5, 2)
            )
        )
        val cart2 = Cart(
            2, 2346, date, product = listOf(
                CartProduct(4, 1),
                CartProduct(6, 2)
            )
        )

        cartsDao.insertCart(cart)
        cartsDao.insertCart(cart2)

        // create updated cart
        val updatedCart = Cart(
            1, 2345, date, product = listOf(
                CartProduct(4, 1),
                CartProduct(3, 1)
            )
        )

        // update
        cartsDao.updateProductInCart(updatedCart.userId, updatedCart.product)

        // get word and assert if it equals to updated word
        val result = cartsDao.getUserCart(updatedCart.userId)
        assertTrue(result != null)
        assertThat(result?.id).isEqualTo(updatedCart.id)

    }

}