package com.tei.snapshop.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tei.snapshop.feature_cart.data.CartProduct
import java.util.Date

/**
 * Class Description
 * Created by Eniola Ipoola on 16/08/2024.
 * Copyright (c). All rights reserved
 */
class CartProductConverter {

    @TypeConverter
    fun fromCartProduct(cardProduct: List<CartProduct>?) : String? {
        return if(cardProduct == null) {
            null
        } else {
            Gson().toJson(cardProduct)
        }
    }

    @TypeConverter
    fun toCardProductList(cardProduct: String?): List<CartProduct>? {
        return if(cardProduct == null) {
            null
        } else {
            val type = object : TypeToken<List<CartProduct>>() {}.type
            Gson().fromJson<List<CartProduct>>(cardProduct, type)
        }
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}