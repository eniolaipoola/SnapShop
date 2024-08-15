package com.tei.snapshop.feature_products.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.tei.snapshop.feature_products.data.Product
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
    backHandler: () -> Unit,
    product: Product?
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
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top back button and product details text
            PageTitle(modifier, backHandler = backHandler,
                pageTitle = stringResource(id = R.string.product_details)
            )

            Spacer(modifier = modifier.height(16.dp))

            val mainImagePainter: Painter = rememberAsyncImagePainter(model = remember {product?.image})
            Image(
                painter = mainImagePainter,
                contentDescription = stringResource(R.string.product_image),
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .height(250.dp).padding(16.dp)
            )

            Spacer(modifier = modifier.height(16.dp))

            // Product title and price
            product?.title?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.black),
                    style = AppTypography.bodyMedium,
                    modifier = modifier.align(Alignment.Start)
                )
            }
            Spacer(modifier = modifier.height(16.dp))

            Text(
                text = """${"$"}${product?.price}""",
                style = AppTypography.bodyMedium,
                fontSize = 20.sp,
                maxLines = 2,
                color = colorResource(id = R.color.black),
                modifier = modifier.align(Alignment.Start)
            )

            Spacer(modifier = modifier.height(16.dp))

            // Product description
            product?.description?.let {
                Text(
                    text = it,
                    color = colorResource(id = R.color.black),
                    style = AppTypography.bodySmall,
                    fontSize = 14.sp,
                    modifier = modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = modifier.height(16.dp))


            Spacer(modifier = modifier.height(35.dp))

            // Add to Cart button
            CustomAppButton(
                modifier,
                buttonText = stringResource(id = R.string.add_to_cart),
                enabled = true,
                onButtonClicked = {

                }
            )
        }
    }
}
