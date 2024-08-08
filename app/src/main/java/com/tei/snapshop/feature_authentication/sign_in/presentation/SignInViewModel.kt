package com.tei.snapshop.feature_authentication.sign_in.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tei.snapshop.data.di.DispatcherProvider
import com.tei.snapshop.feature_authentication.sign_in.data.repository.SignInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Viewmodel class that handles all signin functional calls
 * Created by Eniola Ipoola on 10/05/2024.
 * Copyright (c). All rights reserved
 */
@HiltViewModel
class SignInViewModel @Inject constructor(
    private val dispatcher: DispatcherProvider,
    private val repository: SignInRepository
) : ViewModel() {

    var email = mutableStateOf("")
        private set

    var password = mutableStateOf("")
        private set

    var isSignInButtonEnabled = mutableStateOf(false)
        private set

    var isPasswordVisible = mutableStateOf(false)
        private set

    fun onEmailChanged(emailValue: String) {
        email.value = emailValue
        updateLoginButtonState()
    }

    fun onPasswordChanged(passwordValue: String) {
        password.value = passwordValue
        updateLoginButtonState()
    }

    private fun updateLoginButtonState() {
        isSignInButtonEnabled.value = email.value.isNotBlank() && password.value.isNotBlank()
    }

    fun signInUser() {
        //TODO
    }

    fun onPasswordVisibilityToggle() {
        isPasswordVisible.value = !isPasswordVisible.value
    }
}