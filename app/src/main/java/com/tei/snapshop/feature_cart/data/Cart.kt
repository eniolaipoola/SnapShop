package com.tei.snapshop.feature_cart.data

/**
 * Class Description
 * Created by Eniola Ipoola on 12/08/2024.
 * Copyright (c). All rights reserved
 */

data class Cart(
    val id: Int,
    val userId: Int,
    val product: List<CartProduct>
)

data class CartProduct(
    val productId: Int,
    val quantity: Int
)