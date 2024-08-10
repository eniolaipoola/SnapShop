package com.tei.snapshop.feature_authentication.sign_up.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tei.snapshop.R
import com.tei.snapshop.feature_authentication.sign_in.presentation.ui.ChangeAuthModeText
import com.tei.snapshop.feature_authentication.sign_in.presentation.ui.SocialAuthButtons
import com.tei.snapshop.feature_authentication.sign_up.SignUpViewModel
import com.tei.snapshop.ui.CustomAppButton
import com.tei.snapshop.ui.EmailInput
import com.tei.snapshop.ui.NavScreen
import com.tei.snapshop.ui.PasswordInput
import com.tei.snapshop.ui.theme.AppTypography
import com.tei.snapshop.ui.theme.shapes

/**
 * Class Description
 * Created by Eniola Ipoola on 10/08/2024.
 * Copyright (c). All rights reserved
 */

@Composable
fun SignUpScreen(navController: NavController) {
    SignUpContent(modifier = Modifier, navController = navController)
}

@Composable
fun SignUpContent(
    modifier: Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavController
){

    val email by remember { viewModel.email }
    val password by remember { viewModel.password }
    val username by remember { viewModel.username }
    val confirmPassword by remember { viewModel.username }
    val isSignUpButtonEnabled by remember { viewModel.isSignUpButtonEnabled }


    Surface(
        color = Color.White,
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
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
                Spacer(modifier.height(10.dp))
                EmailInput(
                    email = email,
                    modifier = modifier,
                    onEmailChange = {} //viewModel::onEmailChanged
                )
                Spacer(modifier.height(10.dp))
                UsernameInput(
                    username = username,
                    modifier = modifier,
                    onEmailChange = {} //viewModel::onEmailChanged
                )
                Spacer(modifier.height(10.dp))
                PasswordInput(
                    password = password,
                    modifier = modifier,
                    onPasswordChange =  {}, //viewModel::onPasswordChanged,
                    onPasswordVisibilityToggle = {}, // viewModel::onPasswordVisibilityToggle,
                    isPasswordVisible = true //passwordVisibility
                )
                Spacer(modifier.height(10.dp))
                ConfirmPasswordInput(
                    password = confirmPassword,
                    modifier = modifier,
                    onPasswordChange =  {}, //viewModel::onPasswordChanged,
                    onPasswordVisibilityToggle = {}, // viewModel::onPasswordVisibilityToggle,
                    isPasswordVisible = true //passwordVisibility
                )
                Spacer(modifier.height(15.dp))

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

                Spacer(modifier.height(15.dp))

                CustomAppButton(
                    modifier,
                    buttonText = stringResource(id = R.string.sign_up)
                ) {
                    if (isSignUpButtonEnabled) {
                        //Go to landing page
                        navController.navigate(NavScreen.LandingPage.route)
                    }
                }

                ChangeAuthModeText(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
                    onClick = {
                        navController.navigate(NavScreen.SignIn.route)
                    },
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
    onEmailChange: (String) -> Unit
) {
    OutlinedTextField(
        value = username.trim(),
        onValueChange =  onEmailChange,
        label = {
            Text(
                text = stringResource(R.string.username),
                color = colorResource(id = R.color.text_color),
                style = AppTypography.bodySmall
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
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
        value = password,
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