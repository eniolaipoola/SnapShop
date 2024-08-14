package com.tei.snapshop.feature_authentication.sign_up.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tei.snapshop.R
import com.tei.snapshop.feature_authentication.sign_in.presentation.ui.ChangeAuthModeText
import com.tei.snapshop.feature_authentication.sign_in.presentation.ui.SocialAuthButtons
import com.tei.snapshop.feature_authentication.sign_in.presentation.ui.getActivity
import com.tei.snapshop.feature_authentication.sign_up.SignUpViewModel
import com.tei.snapshop.ui.CustomAppButton
import com.tei.snapshop.ui.EmailInput
import com.tei.snapshop.ui.PasswordInput
import com.tei.snapshop.ui.theme.AppTypography
import com.tei.snapshop.ui.theme.shapes

/**
 * SignUp Compose screen
 * Created by Eniola Ipoola on 10/08/2024.
 * Copyright (c). All rights reserved
 */

@Composable
fun SignUpScreen(
    navigateToSignIn: () -> Unit,
    navigateToLandingPage: () -> Unit
) {
    SignUpContent(modifier = Modifier, navigateToSignIn = navigateToSignIn, navigateToLandingPage )
}

@Composable
fun SignUpContent(
    modifier: Modifier,
    navigateToSignIn: () -> Unit,
    navigateToHomePage: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
){

    val email by remember { viewModel.email }
    val password by remember { viewModel.password }
    val username by remember { viewModel.username }
    val emailError by remember { viewModel.emailError }
    val userNameError by remember { viewModel.userNameError }
    val passwordError by remember { viewModel.passwordError }
    val confirmPassword by remember { viewModel.confirmPassword }
    val isSignUpButtonEnabled by remember { viewModel.isSignUpButtonEnabled }

    val passwordVisibility by remember { viewModel.isPasswordVisible }

    val signupState by remember { viewModel.signUpState }
    val context = LocalContext.current

    when (signupState) {
        is SignUpState.Idle -> {}
        is SignUpState.Loading -> {
            CircularProgressIndicator() // Show loading indicator
        }
        is SignUpState.Success -> {
            navigateToHomePage()
        }
        is SignUpState.Error -> {
            //show error toast
            Toast.makeText(
                context.getActivity(),
                (signupState as SignUpState.Error).message,
                Toast.LENGTH_SHORT,
            ).show()
        }
    }


    Surface(
        color = Color.White,
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.create_account_description),
                    textAlign = TextAlign.Start,
                    modifier = modifier
                        .fillMaxWidth(),
                    style = AppTypography.titleMedium,
                    color = colorResource(id = R.color.color_primary)
                )
                Spacer(modifier.height(5.dp))
                Text(
                    text = stringResource(R.string.create_account),
                    textAlign = TextAlign.Start,
                    modifier = modifier
                        .fillMaxWidth(),
                    style = AppTypography.bodyMedium,
                    color = colorResource(id = R.color.neutral_500)
                )
                Spacer(modifier.height(5.dp))
                EmailInput(
                    email = email,
                    modifier = modifier,
                    onEmailChange = viewModel::onEmailChanged,
                    error = emailError
                )
                Spacer(modifier.height(5.dp))
                UsernameInput(
                    username = username,
                    modifier = modifier,
                    error = userNameError,
                    onUsernameChange = viewModel::onUsernameChanged
                )

                Spacer(modifier.height(5.dp))
                PasswordInput(
                    password = password,
                    modifier = modifier,
                    onPasswordChange = viewModel::onPasswordChanged,
                    onPasswordVisibilityToggle = viewModel::onPasswordVisibilityToggle,
                    isPasswordVisible = passwordVisibility,
                    error = passwordError
                )
                Spacer(modifier.height(5.dp))
                ConfirmPasswordInput(
                    password = confirmPassword,
                    modifier = modifier,
                    onPasswordChange =  viewModel::onConfirmPasswordChanged,
                    onPasswordVisibilityToggle = viewModel::onConfirmPasswordVisibilityToggle,
                    isPasswordVisible = passwordVisibility
                )
            }

            Column(modifier = modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter))
            {
                SocialAuthButtons(modifier = modifier.fillMaxWidth(),
                    onClick = { },
                    googleButtonClicked = {},
                    firebaseButtonClicked = {},
                    lineText = stringResource(R.string.or_sign_in)
                )

                Spacer(modifier.height(10.dp))

                CustomAppButton(
                    modifier,
                    buttonText = stringResource(id = R.string.sign_up),
                    enabled = isSignUpButtonEnabled,
                    onButtonClicked = {
                        viewModel.signUpUser(email, password)
                    }
                )

                ChangeAuthModeText(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    onClick = { navigateToSignIn() },
                    stringResource(R.string.existing_account_question),
                    stringResource(R.string.sign_in),
                    TextStyle(textAlign = TextAlign.Center)
                )
            }

        }
    }

}

@Composable
fun UsernameInput(
    username: String,
    modifier: Modifier,
    error: String?,
    onUsernameChange: (String) -> Unit
) {
    OutlinedTextField(
        value = username.trim(),
        onValueChange =  onUsernameChange,
        isError = error != null,
        label = {
            Text(
                text = stringResource(R.string.username),
                color = colorResource(id = R.color.text_color),
                style = AppTypography.bodySmall
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        shape = shapes.medium,
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedTextColor = colorResource(id = R.color.text_color),
            unfocusedTextColor = colorResource(id = R.color.text_color),
            focusedIndicatorColor = colorResource(id = R.color.text_field_container_color),
            unfocusedIndicatorColor = colorResource(id = R.color.text_field_container_color),
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        )
    )

    if (error != null) {
        Text(text = error, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelMedium)
    }
}

@Composable
fun ConfirmPasswordInput(
    password: String,
    modifier: Modifier,
    onPasswordChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onPasswordVisibilityToggle: () -> Unit
) {
    OutlinedTextField(
        value = password.trim(),
        onValueChange = onPasswordChange,
        label = { Text(text = stringResource(R.string.confirm_password),
            style = AppTypography.bodySmall,
            color = colorResource(id = R.color.neutral_400)
        )},
        modifier = modifier
            .fillMaxWidth(),
        shape = shapes.medium,
        singleLine = true,
        visualTransformation = when(isPasswordVisible) {
            true ->  VisualTransformation.None
            false -> PasswordVisualTransformation()
        },
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedTextColor = colorResource(id = R.color.text_color),
            unfocusedTextColor = colorResource(id = R.color.text_color),
            unfocusedIndicatorColor = colorResource(id = R.color.text_field_container_color),
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor =  Color.Transparent
        ),
        trailingIcon = {
            IconButton(onClick = onPasswordVisibilityToggle ) {
                when {
                    isPasswordVisible -> {
                        Icon(
                            painter = painterResource(id = R.drawable.password_visible),
                            contentDescription = stringResource(id = R.string.show_password)
                        )
                    }
                    else -> {
                        Icon(
                            painter = painterResource(id = R.drawable.password_invisible_icon),
                            contentDescription = stringResource(id = R.string.hide_password)
                        )
                    }
                }

            }
        }
    )

}