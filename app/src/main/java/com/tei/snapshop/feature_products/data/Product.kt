package com.tei.snapshop.feature_products.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Data class for all app products
 * Created by Eniola Ipoola on 10/08/2024.
 * Copyright (c). All rights reserved
 */

@Parcelize
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
) : Parcelable

@Parcelize
data class ProductRating(
    val rate: Double,
    val count: Int
) : Parcelable

data class ProductCategory(
    val category: List<String>
)