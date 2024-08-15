package com.tei.snapshop.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
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
        enabled = false,
        border = BorderStroke(1.dp, colorResource(id = R.color.text_field_container_color)),
        onClick = {
            onButtonClick()
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
fun LoadingView(modifier: Modifier) {
    Box(
        modifier
            .width(50.dp)
            .height(5.dp)
    ) {
        CircularProgressIndicator(
            color = colorResource(id = R.color.black),
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
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_back_button),
            contentDescription = "Back",
            tint = Color.Black,
            modifier = modifier
                .size(24.dp)
                .clickable { backHandler() }
        )
        Spacer(modifier.width(50.dp))
        Text(
            text = pageTitle,
            fontWeight = FontWeight.Bold,
            style = AppTypography.titleMedium,
            fontSize = 20.sp,
            color = colorResource(id = R.color.black)
        )
    }
}

@Composable
fun NoInternetScreen(onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.no_internet_connection))
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Composable
fun ProductCard2(modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = "",
                placeholder = painterResource(R.drawable.image_placeholder),
                error = painterResource(R.drawable.icon_error)
            ),
            contentDescription = null,
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp),
            alpha = 0.7F,
            contentScale = ContentScale.Inside // ContentScale.Crop makes sure the image fills the entire box
        )
        // Top content (Like button)
        Icon(
            painter = painterResource(id = R.drawable.icon_like),
            tint = Color.White,
            contentDescription = stringResource(R.string.like_button),
            modifier = modifier
                .size(24.dp)
                .align(Alignment.End)
                .background(Color.Black.copy(alpha = 0.5F), shape = CircleShape)
                .padding(4.dp)
        )

        // Bottom content (Name and Price, Cart Icon)
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "product.title",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    style = AppTypography.bodyMedium,
                    textAlign = TextAlign.Left
                )
                Spacer(modifier = modifier.height(4.dp))

                Text(
                    text = "product.price.toString()",
                    color = Color.Black,
                    fontSize = 16.sp,
                    style = AppTypography.bodySmall,
                    textAlign = TextAlign.Left
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.icon_cart),
                contentDescription = "Add to Cart",
                tint = Color.White,
                modifier = modifier
                    .size(24.dp)
                    .background(Color.Black.copy(alpha = 0.5F), shape = CircleShape)
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun ProductDetailExtra(modifier: Modifier) {
    // Size selection
    Text(
        text = "Size",
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        style = AppTypography.bodySmall,
        modifier = modifier
    )

    Spacer(modifier = modifier.height(16.dp))

    var selectedSize by remember { mutableStateOf(40) }
    val sizes = listOf(38, 39, 40, 41, 42, 43)

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(sizes.size) { index ->
            val size = sizes[index]
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        if (size == selectedSize) Color.Black
                        else colorResource(id = R.color.neutral_200),
                        CircleShape
                    )
                    .clickable { selectedSize = size }
            ) {
                Text(
                    text = size.toString(),
                    color = if (size == selectedSize) Color.White else Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}
