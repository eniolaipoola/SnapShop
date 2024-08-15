package com.tei.snapshop.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tei.snapshop.feature_landing_page.LandingPage

/**
 * Class Description
 * Created by Eniola Ipoola on 09/04/2024.
 * Copyright (c). All rights reserved
 */
@Composable
fun AppMainScreen(startDestination: String) {
    val systemUiController = rememberSystemUiController()

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        authenticationNav(navController)

        composable(NavScreen.LandingPage.route) {
            LandingPage()
        }
    }

    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(Color.Black)
        systemUiController.setNavigationBarColor(Color.Transparent)
    }
}
