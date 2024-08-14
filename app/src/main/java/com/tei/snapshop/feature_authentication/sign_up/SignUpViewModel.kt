package com.tei.snapshop.feature_authentication.sign_up

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.tei.snapshop.data.di.DispatcherProvider
import com.tei.snapshop.feature_authentication.sign_up.data.SignUpRepository
import com.tei.snapshop.feature_authentication.sign_up.presentation.ui.SignUpState
import com.tei.snapshop.ui.theme.isValidEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
) : ViewModel() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

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
    var emailError = mutableStateOf<String?>(null)
        private set
    var passwordError = mutableStateOf<String?>(null)
        private set
    var userNameError = mutableStateOf<String?>(null)
        private set

    var isPasswordVisible = mutableStateOf(false)
        private set

    var isConfirmPasswordVisible = mutableStateOf(false)
        private set
    var signupState = mutableStateOf<SignUpState>(SignUpState.Idle)
        private set

    fun onEmailChanged(emailValue: String) {
        email.value = emailValue
        emailError.value = if (isValidEmail(emailValue)) null else "Invalid email address"
        updateSignUpButtonState()
    }

    fun onPasswordChanged(passwordValue: String) {
        password.value = passwordValue
    }

    fun onConfirmPasswordChanged(confirmPasswordValue: String) {
        confirmPassword.value = confirmPasswordValue
        validatePasswordMatch()
    }

    private fun validatePasswordMatch() {
        passwordError.value = if (password.value == confirmPassword.value) null else "Passwords do not match"
        updateSignUpButtonState()
    }

    fun onUsernameChanged(usernameValue: String) {
        username.value = usernameValue
        updateSignUpButtonState()
    }

    fun onPasswordVisibilityToggle() {
        isPasswordVisible.value = !isPasswordVisible.value
    }

    fun onConfirmPasswordVisibilityToggle() {
        isConfirmPasswordVisible.value = !isConfirmPasswordVisible.value
    }

    private fun updateSignUpButtonState() {
        isSignUpButtonEnabled.value = emailError.value == null && passwordError.value == null &&
                email.value.isNotBlank() && password.value.isNotBlank() &&
                username.value.isNotBlank() && confirmPassword.value.isNotBlank() && password.value.isNotBlank()

    }

    fun signUpUser(email: String, password: String) {
        viewModelScope.launch {
            signupState.value = SignUpState.Loading
            try {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            signupState.value = SignUpState.Success
                        } else {
                            signupState.value = SignUpState.Error(task.exception?.message ?: "Signup failed")
                        }
                    }
            } catch (exception: Exception) {
                signupState.value = SignUpState.Error(exception.message ?: "Unexpected error occurred")
            }

        }
    }

}