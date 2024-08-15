package com.tei.snapshop.feature_products.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.tei.snapshop.data.local.RatingConverter

/**
 * Data class for all app products
 * Created by Eniola Ipoola on 10/08/2024.
 * Copyright (c). All rights reserved
 */

@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val image: String,
    val category: String,
    val rating: ProductRating?,
)

data class ProductRating(
    val rate: Double,
    val count: Int
)

data class ProductCategory(
    val category: List<String>
)