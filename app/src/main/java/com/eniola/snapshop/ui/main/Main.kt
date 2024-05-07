package com.eniola.snapshop.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eniola.snapshop.ui.splashscreen.SplashScreen
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

    NavHost(
        navController = navController, startDestination = NavScreen.SplashScreen.route,
        modifier = Modifier
    ) {
        composable(route = NavScreen.SplashScreen.route) {
            SplashScreen()
        }
        composable(
            route = NavScreen.Onboarding.routhWithArgument,
            arguments = listOf(
                navArgument(NavScreen.Onboarding.onboardingArgument) { type = NavType.LongType}
            )
        ) {backStackEntry ->

        }

    }
}

sealed class NavScreen(val route: String) {
    data object SplashScreen : NavScreen("SplashScreen")

    data object Onboarding : NavScreen("Onboarding") {
        const val routhWithArgument: String = "Onboarding/{pageId}"
        const val onboardingArgument: String = "pageId"
    }

    data object Authentication : NavScreen("Authentication") {
        const val routeWithArgument: String = "Authentication/{authId}"
    }
}