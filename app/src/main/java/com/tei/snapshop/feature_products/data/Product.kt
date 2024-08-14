package com.tei.snapshop.feature_products.data

/**
 * Class Description
 * Created by Eniola Ipoola on 10/08/2024.
 * Copyright (c). All rights reserved
 */
data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val description: String,
    val imageUrl: String,
    val category: String?,
)