package com.eniola.snapshop.ui.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.eniola.snapshop.R
import kotlinx.coroutines.delay

/**
 * Class Description
 * Created by Eniola Ipoola on 11/04/2024.
 * Copyright (c). All rights reserved
 */

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier
) {
    LaunchedEffect(key1 = true) {
        delay(3000)

    }
    val appImage = painterResource(id = R.drawable.app_logo)
    Box(modifier) {
        Image(
            painter = appImage,
            contentDescription = stringResource(R.string.app_logo),
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}