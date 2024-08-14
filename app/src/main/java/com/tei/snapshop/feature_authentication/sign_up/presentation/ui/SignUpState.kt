package com.tei.snapshop.feature_authentication.sign_up.presentation.ui

/**
 * Class Description
 * Created by Eniola Ipoola on 14/08/2024.
 * Copyright (c). All rights reserved
 */

sealed class SignUpState {
    object Idle : SignUpState()
    object Loading : SignUpState()
    object Success : SignUpState()
    data class Error(val message: String) : SignUpState()
}