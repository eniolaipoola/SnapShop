package com.tei.snapshop.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tei.snapshop.ui.splashscreen.SplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tei.snapshop.NavScreen
import com.tei.snapshop.ui.feature_onboarding.OnboardingPage

/**
 * Class Description
 * Created by Eniola Ipoola on 09/04/2024.
 * Copyright (c). All rights reserved
 */
@Composable
fun AppMainScreen(startDestination: String) {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()

    NavHost(navController = navController, startDestination = startDestination) {

        composable(NavScreen.SplashScreen.route) {
            SplashScreen()
        }

        composable(NavScreen.OnboardingScreen.route) {
            OnboardingPage()
        }

    }
}
