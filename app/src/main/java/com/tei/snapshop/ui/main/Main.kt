package com.tei.snapshop.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tei.snapshop.ui.splashscreen.SplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Class Description
 * Created by Eniola Ipoola on 09/04/2024.
 * Copyright (c). All rights reserved
 */
@Composable
fun SnapShopMainScreen() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()

    NavHost(navController = navController, startDestination = NavScreen.SplashScreen.route) {
        composable(NavScreen.SplashScreen.route) {
            SplashScreen()
        }

    }
}

sealed class NavScreen(val route: String) {
    object SplashScreen : NavScreen("splashScreen")
}