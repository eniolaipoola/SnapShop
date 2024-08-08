package com.tei.snapshop.feature_authentication.sign_in.presentation.ui

import com.tei.snapshop.feature_authentication.sign_in.data.User


/**
 * Class Description
 * Created by Eniola Ipoola on 20/05/2024.
 * Copyright (c). All rights reserved
 */
sealed interface SignInEvent {
    data class SignInButtonClicked(val email: String, val password: String): SignInEvent
    data class SignUpButtonClicked(val route: String): SignInEvent
    data class ResetPasswordClicked(val route: String) : SignInEvent
    data class SignInError(val errorMessage: String): SignInEvent
    data class SignInSuccessful(val userData: User): SignInEvent

}