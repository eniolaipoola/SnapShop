package com.tei.snapshop.feature_landing_page

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.tei.snapshop.ui.NavScreen

/**
 * Bottom navigation items
 * Created by Eniola Ipoola on 10/08/2024.
 * Copyright (c). All rights reserved
 */
data class BottomNavigationItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = ""
) {
    fun bottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Product",
                icon = Icons.Filled.Home,
                route = NavScreen.Product.route
            ),
            BottomNavigationItem(
                label = "Cart",
                icon = Icons.Filled.ShoppingCart,
                route = NavScreen.Cart.route
            ),
            BottomNavigationItem(
                label = "Settings",
                icon = Icons.Filled.Settings,
                route = NavScreen.Settings.route
            )
        )
    }
}
//icon = Icon(painterResource(id = R.drawable.icon_home), ""),