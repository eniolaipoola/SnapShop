package com.tei.snapshop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tei.snapshop.feature_cart.data.Cart
import com.tei.snapshop.feature_cart.data.local.CartDao
import com.tei.snapshop.feature_products.data.Product
import com.tei.snapshop.feature_products.data.local.ProductDao

/**
 * Class Description
 * Created by Eniola Ipoola on 15/08/2024.
 * Copyright (c). All rights reserved
 */

@Database(entities = [Product::class, Cart::class], version = 1)
@TypeConverters(RatingConverter::class, CartProductConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao() : ProductDao
    abstract fun cartDao() : CartDao
}