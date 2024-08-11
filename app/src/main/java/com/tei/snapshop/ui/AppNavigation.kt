package com.tei.snapshop.ui

/**
 * Class that holds the navigation routes for the whole application
 * Created by Eniola Ipoola on 05/06/2024.
 * Copyright (c). All rights reserved
 */

sealed class NavScreen(val route: String) {

    //Screen Routes
    object SplashScreen : NavScreen("splashScreen")

    object OnboardingScreen : NavScreen("onboarding")

    object SignIn : NavScreen("signIn")
    object SignUp : NavScreen("signUp")
    object Logout : NavScreen("logout")

    object LandingPage : NavScreen("homeScreen")
    object Product : NavScreen("product")
    object Cart : NavScreen("cart")
    object Settings : NavScreen("settings")
    object ProductDetail : NavScreen("product_details")

    //Graph Routes
    data object AuthNav : NavScreen("AUTH_NAV_GRAPH")

    data object HomeNav : NavScreen("HOME_NAV_GRAPH")
}