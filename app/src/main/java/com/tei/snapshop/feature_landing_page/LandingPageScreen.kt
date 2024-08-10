package com.tei.snapshop.feature_landing_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tei.snapshop.R

/**
 * Class Description
 * Created by Eniola Ipoola on 26/06/2024.
 * Copyright (c). All rights reserved
 */

@Composable
fun LandingPageScreen() {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        Column(

        ) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarCompose() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        modifier = Modifier,
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Menu, contentDescription = "Navigation Drawer")
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Favorite, contentDescription = "Action Icon")
            }
        }
    )

}