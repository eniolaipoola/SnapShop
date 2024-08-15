package com.tei.snapshop.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tei.snapshop.feature_authentication.sign_in.presentation.ui.SignInScreen
import com.tei.snapshop.feature_authentication.sign_up.presentation.ui.SignUpScreen
import com.tei.snapshop.feature_cart.presentation.CartScreen
import com.tei.snapshop.feature_onboarding.OnboardingPage
import com.tei.snapshop.feature_products.data.Product
import com.tei.snapshop.feature_products.presentation.ProductDetailsScreen
import com.tei.snapshop.feature_products.presentation.ProductScreen
import com.tei.snapshop.feature_settings.presentation.SettingScreen

/**
 * Class that has both screen nav and graph nav across major features
 * Created by Eniola Ipoola on 10/08/2024.
 * Copyright (c). All rights reserved
 */


fun NavGraphBuilder.authenticationNav(
    navController: NavHostController
) {
    navigation(startDestination = NavScreen.SignIn.route,
        route = NavScreen.AuthNav.route
    ) {

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
            ProductScreen(
                padding = padding,
                onClick = { product ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("product", product)
                    navController.navigate(NavScreen.ProductDetail.route
                    )},
                modifier = Modifier
            )
        }

        composable(route = NavScreen.Cart.route){
            CartScreen(padding = padding, modifier = Modifier, backHandler = {

            })
        }
        composable(route = NavScreen.Settings.route){
            SettingScreen(paddingValues = padding, backHandler = {
                navController.navigate(NavScreen.Settings.route)
            }, modifier = Modifier)
        }

        composable(route = NavScreen.ProductDetail.route){
            val product = navController.previousBackStackEntry?.savedStateHandle?.get<Product>("product")
            ProductDetailsScreen(
                modifier = Modifier,
                padding,
                backHandler = { navController.navigate(NavScreen.Product.route)},
                product = product)
        }
    }
}