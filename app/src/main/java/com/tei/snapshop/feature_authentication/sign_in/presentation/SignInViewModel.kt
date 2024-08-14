package com.tei.snapshop.feature_authentication.sign_in.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.tei.snapshop.data.di.DispatcherProvider
import com.tei.snapshop.feature_authentication.sign_in.data.repository.SignInRepository
import com.tei.snapshop.feature_authentication.sign_in.presentation.ui.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Viewmodel class that handles all signin functional calls
 * Created by Eniola Ipoola on 10/05/2024.
 * Copyright (c). All rights reserved
 */
@HiltViewModel
class SignInViewModel @Inject constructor(
    private val dispatcher: DispatcherProvider,
    private val repository: SignInRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    var email = mutableStateOf("")
        private set

    var password = mutableStateOf("")
        private set

    var isSignInButtonEnabled = mutableStateOf(false)
        private set

    var isPasswordVisible = mutableStateOf(false)
        private set

    var signInState = mutableStateOf<SignInState>(SignInState.Idle)
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

    fun signInUser(email: String, password: String) {
        viewModelScope.launch {
            signInState.value = SignInState.Loading
            try {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    task ->
                    if(task.isSuccessful) {
                        signInState.value = SignInState.Success
                    } else {
                        signInState.value = SignInState.Error(task.exception?.message ?: "SignIn failed")
                    }
                }
            } catch (exception: Exception) {
                signInState.value = SignInState.Error(exception.message ?: "")
            }
        }
    }

    fun onPasswordVisibilityToggle() {
        isPasswordVisible.value = !isPasswordVisible.value
    }
}