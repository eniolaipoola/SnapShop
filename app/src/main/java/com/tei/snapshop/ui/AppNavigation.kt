package com.tei.snapshop.ui

/**
 * Class that holds the navigation routes for the whole application
 * Created by Eniola Ipoola on 05/06/2024.
 * Copyright (c). All rights reserved
 */

sealed class NavScreen(val route: String) {
    object SplashScreen : NavScreen("splashScreen")

    object OnboardingScreen : NavScreen("onboarding")

    object SignIn : NavScreen("signIn")
    object SignUp : NavScreen("signUp")

    object LandingPage : NavScreen("homeScreen")
}