package com.tei.snapshop.feature_landing_page.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tei.snapshop.R
import com.tei.snapshop.feature_landing_page.ProductViewModel
import com.tei.snapshop.ui.theme.AppTypography
import com.tei.snapshop.ui.theme.shapes

/**
 * Class Description
 * Created by Eniola Ipoola on 10/08/2024.
 * Copyright (c). All rights reserved
 */

@Composable
fun HomeScreen(
    viewModel: ProductViewModel = hiltViewModel(),
    padding: PaddingValues
) {
    val searchQuery by remember { viewModel.searchQuery }
    val sampleData = listOf("")

    Surface(
        color = Color.White,
        modifier = Modifier.padding(padding)
            .fillMaxSize()
            .background(Color.White).padding(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = viewModel::onSearchQueryChanged ,
                    label = { Text(text = stringResource(R.string.search),
                        style = AppTypography.bodySmall) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = shapes.medium,
                    singleLine = true,
                    maxLines = 1,
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = colorResource(id = R.color.text_color),
                        unfocusedTextColor = colorResource(id = R.color.text_color),
                        unfocusedIndicatorColor = colorResource(id = R.color.text_field_container_color),
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        errorContainerColor =  Color.Transparent
                    ),
                    leadingIcon = {
                        IconButton(onClick = {}) {
                            Icon(painter = painterResource(id = R.drawable.icon_search), contentDescription = null)
                        }
                    },
                    trailingIcon = {
                        IconButton(onClick = {} ) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_camera),
                                contentDescription = null
                            )
                        }
                    }
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }
        }
    }
}