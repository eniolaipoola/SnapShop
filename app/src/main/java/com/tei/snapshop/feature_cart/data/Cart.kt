package com.tei.snapshop.feature_cart.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

/**
 * Class Description
 * Created by Eniola Ipoola on 12/08/2024.
 * Copyright (c). All rights reserved
 */


@Parcelize
@Entity(tableName = "carts")
data class Cart(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val date: Date,
    val product: List<CartProduct>
) : Parcelable

@Parcelize
data class CartProduct(
    val productId: Int,
    val quantity: Int
): Parcelable