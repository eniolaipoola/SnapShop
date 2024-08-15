package com.tei.snapshop.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tei.snapshop.feature_products.data.ProductRating

/**
 * Class Description
 * Created by Eniola Ipoola on 15/08/2024.
 * Copyright (c). All rights reserved
 */
class RatingConverter {
    @TypeConverter
    fun fromProductRating(rating: ProductRating?): String? {
        return if (rating == null) {
            null
        } else {
            Gson().toJson(rating)
        }
    }

    @TypeConverter
    fun toProductRating(ratingString: String?): ProductRating? {
        return if (ratingString == null) {
            null
        } else {
            val type = object : TypeToken<ProductRating>() {}.type
            Gson().fromJson<ProductRating>(ratingString, type)
        }
    }
}