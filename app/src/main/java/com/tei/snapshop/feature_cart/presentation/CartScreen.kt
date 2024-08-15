package com.tei.snapshop.feature_cart.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
 * Created by Eniola Ipoola on 10/08/2024.
 * Copyright (c). All rights reserved
 */

@Composable
fun CartScreen(
    padding: PaddingValues,
    modifier: Modifier,
    backHandler: () -> Unit
) {

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
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .fillMaxSize()
            ) {
                PageTitle(modifier, backHandler = backHandler,
                    pageTitle = stringResource(id = R.string.cart)  )

                Spacer(modifier = modifier.height(16.dp))

                CartItem(modifier)

                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.Center
                ) {
                    Text(
                        text = "Total Price: ",
                        textAlign = TextAlign.Start,
                        modifier = modifier
                            .padding(top = 4.dp)
                            .fillMaxWidth(),
                        style = AppTypography.bodyMedium,
                        color = colorResource(id = R.color.color_primary)
                    )

                    Text(
                        text = "$300",
                        textAlign = TextAlign.Start,
                        modifier = modifier
                            .padding(top = 4.dp)
                            .fillMaxWidth(),
                        style = AppTypography.bodyLarge,
                        color = colorResource(id = R.color.color_primary)
                    )
                }

                // Add to Cart button
                CustomAppButton(
                    modifier,
                    buttonText = stringResource(id = R.string.checkout),
                    enabled = true,
                    onButtonClicked = {

                    }
                )
            }
        }
    }

}

@Composable
fun CartItem(modifier: Modifier) {
    val cart = listOf(
        Product(1,"Jacket", 100.0, description = "Product", "https://i.pravatar.cc/", "Men", null),
        Product(2,  "Pant", price = 20.0, description = "Product",  "https://i.pravatar.cc/",  "Women", null),
        Product(3,"Jacket", 80.0, description = "Product","https://i.pravatar.cc/", "kids", null),
        Product(4,"Dress", 50.0, description = "Product","https://i.pravatar.cc/", category = "Men", null)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(cart.size) { index ->
            CartCard(product = cart[index], modifier)
        }
    }
}

@Composable
fun CartCard(product: Product, modifier: Modifier) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .padding(8.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White),
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = product.image,
                    placeholder = painterResource(R.drawable.image_placeholder),
                    error = painterResource(R.drawable.icon_error)
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight(),
                alpha = 0.7F,
                contentScale = ContentScale.FillHeight // ContentScale.Crop makes sure the image fills the entire box
            )

            Spacer(modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .padding(start = 16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(
                    text = product.title,
                    textAlign = TextAlign.Start,
                    modifier = modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth(),
                    style = AppTypography.bodyMedium,
                    color = colorResource(id = R.color.color_primary)
                )
                Spacer(modifier.height(14.dp))

                Text(
                    text = "Color",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    style = AppTypography.bodyMedium,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier.height(14.dp))

                Text(
                    text = "2",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    style = AppTypography.bodyMedium,

                    textAlign = TextAlign.Left
                )

                Spacer(modifier.height(14.dp))

                Text(
                    text = product.price.toString(),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    style = AppTypography.bodyMedium,
                    textAlign = TextAlign.Left
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartItemPreview() {
    CartScreen(padding = PaddingValues(4.dp), modifier = Modifier, backHandler = {})
   // CartItem(modifier = Modifier)
}

