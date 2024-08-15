package com.tei.snapshop.feature_products.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.tei.snapshop.R
import com.tei.snapshop.feature_products.ProductViewModel
import com.tei.snapshop.feature_products.data.Product
import com.tei.snapshop.ui.LoadingView
import com.tei.snapshop.ui.theme.AppTypography
import com.tei.snapshop.ui.theme.shapes

/**
 * Class Description
 * Created by Eniola Ipoola on 10/08/2024.
 * Copyright (c). All rights reserved
 */

@Composable
fun ProductScreen(
    viewModel: ProductViewModel = hiltViewModel(),
    onClick: () -> Unit,
    padding: PaddingValues,
    modifier: Modifier
) {
    val searchQuery by remember { viewModel.searchQuery }

    val products by viewModel.products.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
    }

    Surface(
        color = Color.White,
        modifier = modifier
            .padding(padding)
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = viewModel::onSearchQueryChanged ,
                    label = { Text(text = stringResource(R.string.search),
                        style = AppTypography.bodySmall) },
                    modifier = modifier
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
                Spacer(modifier = modifier.height(10.dp))
                //horizontal category recyclerview
                ProductCategory(modifier)
                Spacer(modifier = modifier.height(10.dp))
                //vertical product recyclerview
                ProductListGrid(products, onClick, modifier)
            }
        }
    }
}

@Composable
fun ProductCategory(modifier: Modifier) {
    val categories = listOf("All", "Woman", "Man", "Kids")

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(categories.size) { index ->
            Card(
                modifier = modifier
                    .background(color = colorResource(id = R.color.neutral_100))
                    .wrapContentSize()
                    .border(shape = shapes.large, width = 0.5.dp, color = colorResource(id = R.color.neutral_200)),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.white),
                ),

            ) {
                Text(
                    text = categories[index],
                    modifier = modifier.padding(12.dp),
                    color = colorResource(id = R.color.neutral_500),
                )
            }
        }
    }
}

@Composable
fun ProductListGrid(products: List<Product>, onClick: () -> Unit, modifier: Modifier) {

    if(products.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(products.size) { index ->
                ProductCard(product = products[index], onClick, modifier)
            }
        }
    } else {
        // Show a loading indicator while fetching the products
        LoadingView(modifier = modifier)
    }

}

@Composable
fun ProductCard(product: Product, onProductClicked: () -> Unit, modifier: Modifier) {
    Box(
        modifier = modifier
            .height(250.dp)
            .fillMaxWidth()
            .clickable {
                onProductClicked()
            }
    ) {
        // Image as the background
        Image(
            painter = rememberAsyncImagePainter(
                model = product.image,
                placeholder = painterResource(R.drawable.image_placeholder),
                error = painterResource(R.drawable.icon_error)
            ),
            contentDescription = null,
            modifier = modifier
                .fillMaxSize(),
            alpha = 0.7F,
            contentScale = ContentScale.FillHeight // ContentScale.Crop makes sure the image fills the entire box
        )

        Column(
            modifier = modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
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
                        text = product.title,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        style = AppTypography.bodyMedium,
                        textAlign = TextAlign.Left
                    )
                    Spacer(modifier = modifier.height(4.dp))

                    Text(
                        text = product.price.toString(),
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


}