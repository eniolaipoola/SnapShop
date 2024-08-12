package com.tei.snapshop.feature_products.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.tei.snapshop.R
import com.tei.snapshop.ui.CustomAppButton
import com.tei.snapshop.ui.PageTitle
import com.tei.snapshop.ui.theme.AppTypography


/**
 * Class Description
 * Created by Eniola Ipoola on 11/08/2024.
 * Copyright (c). All rights reserved
 */

@Composable
fun ProductDetailsScreen(
    modifier: Modifier,
    padding: PaddingValues,
    backHandler: () -> Unit
) {
    Surface(
        color = Color.White,
        modifier = modifier
            .padding(padding)
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {

        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top back button and product details text
            PageTitle(modifier, backHandler = backHandler,
                pageTitle = stringResource(id = R.string.product_details)
            )

            Spacer(modifier = modifier.width(16.dp))

            val mainImagePainter: Painter = rememberAsyncImagePainter(model = "https://via.placeholder.com/300")
            Image(
                painter = mainImagePainter,
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(modifier = modifier.height(16.dp))

            // Product title and price
            Text(
                text = "Sports tech",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                style = AppTypography.bodyLarge,
                modifier = modifier.align(Alignment.Start)
            )
            Spacer(modifier = modifier.height(8.dp))

            Text(
                text = "$457.800",
                fontWeight = FontWeight.Bold,
                style = AppTypography.bodyMedium,
                fontSize = 20.sp,
                modifier = modifier.align(Alignment.Start)
            )

            Spacer(modifier = modifier.height(8.dp))

            // Product description
            Text(
                text = "Sports tech. Street smarts. Made in Portugal, Bolster blends luxurious leathers to everyday cool. Be bold. Be you.",
                color = Color.Gray,
                style = AppTypography.bodySmall,
                fontSize = 14.sp,
                modifier = modifier.align(Alignment.Start)
            )

            Spacer(modifier = modifier.height(16.dp))

            // Size selection
            Text(
                text = "Size",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                style = AppTypography.bodySmall,
                modifier = modifier.align(Alignment.Start)
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

            Spacer(modifier = modifier.height(35.dp))

            // Add to Cart button
            CustomAppButton(
                modifier,
                buttonText = stringResource(id = R.string.add_to_cart)
            ) {
                //Add to cart
            }
        }
    }
}
