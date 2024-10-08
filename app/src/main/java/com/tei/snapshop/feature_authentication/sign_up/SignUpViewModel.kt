package com.tei.snapshop.feature_authentication.sign_up

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.tei.snapshop.data.di.DispatcherProvider
import com.tei.snapshop.feature_authentication.sign_in.data.User
import com.tei.snapshop.feature_authentication.sign_in.presentation.SignInViewModel
import com.tei.snapshop.feature_authentication.sign_up.data.SignUpRepository
import com.tei.snapshop.feature_authentication.sign_up.presentation.ui.SignUpState
import com.tei.snapshop.ui.theme.isValidEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Class Description
 * Created by Eniola Ipoola on 10/08/2024.
 * Copyright (c). All rights reserved
 */

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val dispatcher: DispatcherProvider,
    private val repository: SignUpRepository,
    private val firebaseAuth: FirebaseAuth,
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : ViewModel() {

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
    var signUpState = mutableStateOf<SignUpState>(SignUpState.Idle)
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
            signUpState.value = SignUpState.Loading
            try {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            signUpState.value = SignUpState.Success
                            val firebaseUser = task.result?.user
                            val user = firebaseUser.let {
                                User(displayName = it?.displayName, email = it?.email,
                                    phoneNumber = it?.phoneNumber, uid = it?.uid
                                )
                            }
                            saveUserData(user)
                        } else {
                            signUpState.value = SignUpState.Error(task.exception?.message ?: "Signup failed")
                        }
                    }
            } catch (exception: Exception) {
                signUpState.value = SignUpState.Error(exception.message ?: "Unexpected error occurred")
            }
        }
    }

    private fun saveUserData(user: User) {
        val userJson = gson.toJson(user)
        viewModelScope.launch {
            withContext(dispatcher.io) {
                sharedPreferences.edit().apply {
                    putString(SignInViewModel.DATA, userJson)
                    apply()
                }
            }
        }
    }

}