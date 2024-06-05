package com.tei.snapshop.ui.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tei.snapshop.R

/**
 * Class Description
 * Created by Eniola Ipoola on 11/04/2024.
 * Copyright (c). All rights reserved
 */
@Composable
fun SplashScreen()
{
    Box(modifier = Modifier
        .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Image(modifier = Modifier,
            contentDescription = stringResource(R.string.splashscreen),
            painter = painterResource(id = R.drawable.ic_launcher_foreground)
        )
    }
}

@Preview
@Composable
fun PreviewSplashPage() {
    SplashScreen()
}