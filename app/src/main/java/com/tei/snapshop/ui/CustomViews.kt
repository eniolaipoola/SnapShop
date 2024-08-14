package com.tei.snapshop.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tei.snapshop.R
import com.tei.snapshop.ui.theme.AppTypography
import com.tei.snapshop.ui.theme.shapes

/**
 * Class Description
 * Created by Eniola Ipoola on 08/08/2024.
 * Copyright (c). All rights reserved
 */

@Composable
fun EmailInput(
    email: String,
    modifier: Modifier,
    error: String?,
    onEmailChange: (String) -> Unit
) {
    OutlinedTextField(
        value = email.trim(),
        onValueChange =  onEmailChange,
        isError = error != null,
        label = {
            Text(
                text = stringResource(R.string.email),
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
fun PasswordInput(
    password: String,
    modifier: Modifier,
    onPasswordChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    error: String?,
    onPasswordVisibilityToggle: () -> Unit
) {
    OutlinedTextField(
        value = password.trim(),
        onValueChange = onPasswordChange,
        label = { Text(text = stringResource(R.string.password),
            style = AppTypography.bodySmall) },
        modifier = modifier
            .fillMaxWidth(),
        shape = shapes.medium,
        singleLine = true,
        isError = error != null,
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

    if (error != null) {
        Text(text = error, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelMedium)
    }
}


@Composable
fun CustomAppButton(
    modifier: Modifier,
    buttonText: String,
    onButtonClicked: () -> Unit,
    enabled: Boolean
) {
    Button(
        shape = shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.color_primary),
            disabledContainerColor = colorResource(id = R.color.button_inactive_color),
            disabledContentColor = colorResource(id = R.color.color_primary)
        ),
        enabled = enabled,
        border = BorderStroke(1.dp, colorResource(id = R.color.text_field_container_color)),
        onClick = onButtonClicked
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Text(
                    modifier = modifier
                        .padding(start = 16.dp, end = 16.dp),
                    text = buttonText,
                    color = colorResource(id = R.color.text_color),
                    textAlign = TextAlign.Center,
                    style = AppTypography.bodySmall,
                    fontSize = 16.sp
                )
            }

        }
    }

}

@Composable
fun AuthButtonComposable(
    modifier: Modifier,
    buttonText: String,
    image: Painter,
    onButtonClick: () -> Unit
) {
    Button(
        shape = shapes.extraLarge,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            disabledContainerColor = colorResource(id = R.color.button_inactive_color)
        ),
        enabled = true,
        border = BorderStroke(1.dp, colorResource(id = R.color.text_field_container_color)),
        onClick = {
            onButtonClick
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Image(
                    painter = image,
                    contentDescription = stringResource(id = R.string.sign_in_with_google),
                )

                Text(
                    modifier = modifier
                        .padding(start = 16.dp, end = 16.dp),
                    text = buttonText,
                    color = colorResource(id = R.color.black),
                    textAlign = TextAlign.Center,
                    style = AppTypography.bodySmall,
                    fontSize = 16.sp
                )
            }

        }
    }
}

@Composable
fun ErrorView(
    errorMessage : String,
    modifier: Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_error),
            contentDescription = stringResource(R.string.error_message),
            alignment = Alignment.Center,
            modifier = modifier
                .height(20.dp)
                .width(20.dp)
        )
        Text(
            text = errorMessage,
            textAlign = TextAlign.Start,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically),
            style = AppTypography.bodySmall,
            color = colorResource(id = R.color.black)
        )
    }
}

@Composable
fun SuccessView(
    message : String,
    modifier: Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_success),
            contentDescription = stringResource(R.string.success_message),
            alignment = Alignment.Center,
            modifier = modifier
                .height(20.dp)
                .width(20.dp)
        )
        Text(
            text = message,
            textAlign = TextAlign.Start,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically),
            style = AppTypography.bodySmall,
            color = colorResource(id = R.color.color_primary)
        )
    }
}

@Composable
fun PageTitle(
    modifier: Modifier,
    backHandler: () -> Unit,
    pageTitle: String
) {
    // Top back button and product details text
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_back_button),
            contentDescription = "Back",
            modifier = modifier
                .size(24.dp)
                .clickable { backHandler() }
        )
        Spacer(modifier = modifier.width(16.dp))
        Text(
            text = pageTitle,
            fontWeight = FontWeight.Bold,
            style = AppTypography.titleMedium,
            fontSize = 20.sp
        )
    }
}
