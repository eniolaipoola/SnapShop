package com.tei.snapshop.feature_authentication.sign_up

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tei.snapshop.data.di.DispatcherProvider
import com.tei.snapshop.feature_authentication.sign_up.data.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Class Description
 * Created by Eniola Ipoola on 10/08/2024.
 * Copyright (c). All rights reserved
 */

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val dispatcher: DispatcherProvider,
    private val repository: SignUpRepository
): ViewModel() {

    var email = mutableStateOf("")
        private set

    var username = mutableStateOf("")
        private set
    var password = mutableStateOf("")
        private set

    var confirmPassword = mutableStateOf("")
        private set

    var isSignUpButtonEnabled = mutableStateOf(false)
        private set

    var isPasswordMatching = mutableStateOf(false)
        private set

    fun onEmailChanged(emailValue: String) {
        email.value = emailValue
        updateSignUpButtonState()
    }

    fun onPasswordChanged(passwordValue: String) {
        password.value = passwordValue
        updateSignUpButtonState()
    }

    fun onConfirmPasswordChanged(confirmPasswordValue: String) {
        password.value = confirmPasswordValue
        updateSignUpButtonState()
    }

    fun onUsernameChanged(usernameValue: String) {
        password.value = usernameValue
        updateSignUpButtonState()
    }

    private fun updateSignUpButtonState() {
        if(password.value === confirmPassword.value) {
            isPasswordMatching.value = true
        }
        isSignUpButtonEnabled.value = email.value.isNotBlank() && password.value.isNotBlank() &&
                username.value.isNotBlank() && isPasswordMatching.value == true
    }

    fun signUpUser() {
        //TODO
    }

}