package com.tei.snapshop.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tei.snapshop.feature_splashscreen.SplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tei.snapshop.feature_authentication.sign_in.presentation.ui.SignInScreen
import com.tei.snapshop.feature_authentication.sign_up.presentation.ui.SignUpScreen
import com.tei.snapshop.feature_onboarding.OnboardingPage
import com.tei.snapshop.feature_landing_page.LandingPageScreen

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

        composable(NavScreen.LandingPage.route) {
            LandingPageScreen()
        }

        composable(NavScreen.SignIn.route) {
            SignInScreen(navController)
        }

        composable(NavScreen.SignUp.route) {
            SignUpScreen(navController)
        }

    }

    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(Color.Black)
        systemUiController.setNavigationBarColor(Color.Transparent)
    }
}
