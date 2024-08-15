package com.tei.snapshop.feature_authentication.sign_in.presentation.ui


/**
 * Class that manages signIn screen interaction
 * Created by Eniola Ipoola on 20/05/2024.
 * Copyright (c). All rights reserved
 */
sealed class SignInState {
    object Idle : SignInState()
    object Loading : SignInState()
    object Success : SignInState()
    data class Error(val message: String) : SignInState()
}