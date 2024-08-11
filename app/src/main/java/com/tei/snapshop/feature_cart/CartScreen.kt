package com.tei.snapshop.feature_cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Class Description
 * Created by Eniola Ipoola on 10/08/2024.
 * Copyright (c). All rights reserved
 */

@Composable
fun CartScreen(padding: PaddingValues) {

    Surface(
        color = Color.White,
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }
        }
    }

}