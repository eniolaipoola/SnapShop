package com.tei.snapshop.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tei.snapshop.feature_authentication.sign_in.presentation.ui.SignInScreen
import com.tei.snapshop.feature_authentication.sign_up.presentation.ui.SignUpScreen
import com.tei.snapshop.feature_landing_page.compose.CartScreen
import com.tei.snapshop.feature_landing_page.compose.HomeScreen
import com.tei.snapshop.feature_landing_page.compose.SettingScreen
import com.tei.snapshop.feature_onboarding.OnboardingPage

/**
 * Class that has both screen nav and graph nav across major features
 * Created by Eniola Ipoola on 10/08/2024.
 * Copyright (c). All rights reserved
 */


fun NavGraphBuilder.authenticationNav(
    navController: NavHostController
) {
    navigation(startDestination = NavScreen.SignIn.route,
        route = NavScreen.AuthNav.route) {

        composable(NavScreen.OnboardingScreen.route) {
            OnboardingPage()
        }

        composable(NavScreen.SignIn.route) {
            SignInScreen(
                navigateToHomePage = {
                    navController.navigate(NavScreen.LandingPage.route)
                },
                navigateToSignUp = {
                    navController.navigate(NavScreen.SignUp.route)
                }
            )
        }

        composable(NavScreen.SignUp.route) {
            SignUpScreen(navigateToSignIn = {
                navController.navigate(NavScreen.SignIn.route)},
                navigateToLandingPage = {
                    navController.navigate(NavScreen.LandingPage.route)
                })
        }
    }
}

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    padding: PaddingValues,
    logout: () -> Unit
) {
    NavHost(
        navController = navController,
        route = NavScreen.HomeNav.route,
        startDestination = NavScreen.Product.route
    ) {
        composable(route = NavScreen.Product.route){
            HomeScreen(padding = padding)
        }

        composable(route = NavScreen.Cart.route){
            CartScreen()
        }

        composable(route = NavScreen.Settings.route){
            SettingScreen()
        }
    }
}