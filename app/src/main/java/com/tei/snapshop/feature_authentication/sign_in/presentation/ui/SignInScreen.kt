package com.tei.snapshop.feature_authentication.sign_in.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tei.snapshop.R
import com.tei.snapshop.feature_authentication.sign_in.presentation.SignInViewModel
import com.tei.snapshop.ui.CustomAppButton
import com.tei.snapshop.ui.EmailInput
import com.tei.snapshop.ui.PasswordInput
import com.tei.snapshop.ui.theme.AppTypography

/**
 * Sign in compose screen
 * Created by Eniola Ipoola on 10/05/2024.
 * Copyright (c). All rights reserved
 */

@Composable
fun SignInScreen(
    modifier: Modifier,
    viewModel: SignInViewModel = hiltViewModel()
) {

    val email by remember { viewModel.email }
    val password by remember { viewModel.password }
    val passwordVisibility by remember {
        viewModel.isPasswordVisible
    }
    val isSignInButtonEnabled by remember { viewModel.isSignInButtonEnabled }


    Surface(
        color = Color.White,
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.welcome_back_text),
                    textAlign = TextAlign.Start,
                    modifier = modifier
                        .fillMaxWidth(),
                    style = AppTypography.titleLarge,
                    color = colorResource(id = R.color.color_primary)
                )

                Text(
                    text = stringResource(R.string.sign_in_text),
                    textAlign = TextAlign.Start,
                    modifier = modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    style = AppTypography.titleLarge,
                    color = colorResource(id = R.color.color_primary)
                )


                EmailInput(
                    email = email,
                    modifier = modifier,
                    onEmailChange = viewModel::onEmailChanged
                )

                Spacer(modifier.height(15.dp))

                PasswordInput(
                    password = password,
                    modifier = modifier,
                    onPasswordChange = viewModel::onPasswordChanged,
                    onPasswordVisibilityToggle = viewModel::onPasswordVisibilityToggle,
                    isPasswordVisible = passwordVisibility
                )

                Spacer(modifier.height(15.dp))

                Text(
                    text = stringResource(R.string.forgot_password_question),
                    textAlign = TextAlign.End,
                    modifier = modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    style = AppTypography.bodyMedium,
                    color = colorResource(id = R.color.color_primary)
                )

                Spacer(modifier.height(15.dp))

                CustomAppButton(
                    modifier,
                    buttonText = stringResource(id = R.string.sign_in)
                ) {
                    if (isSignInButtonEnabled) {
                        viewModel::signInUser
                    } else {
                        //error view that email or password cannot be null
                    }
                }

            }

            Column(modifier = modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter))
            {
                SocialAuthButtons(modifier = modifier.fillMaxWidth(),
                    onClick = { },
                    googleButtonClicked = {},
                    firebaseButtonClicked = {},
                    proTipText = null
                )

                ChangeAuthModeText(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
                    onClick = {},
                    stringResource(R.string.no_account_question),
                    stringResource(R.string.sign_up),
                    TextStyle(textAlign = TextAlign.Center)
                )
            }
        }

    }
}


@Preview
@Composable
fun LoginScreen() {
    SignInScreen(modifier = Modifier)
}