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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.tei.snapshop.R
import com.tei.snapshop.feature_products.ProductViewModel
import com.tei.snapshop.feature_products.data.Product
import com.tei.snapshop.ui.ErrorView
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
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchInitialProducts()
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

                if(error != null) {
                    ErrorView(errorMessage = error.toString(), modifier = modifier)
                }

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
fun ProductListGrid(
    products: List<Product>,
    onClick: () -> Unit,
    modifier: Modifier,
    viewModel: ProductViewModel = hiltViewModel()
) {

    if(products.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(products.size) { index ->
                ProductCard(product = products[index], onClick, modifier)
                // Load next batch when reaching the end of the list
                if (index == products.size - 1) {
                    viewModel.loadNextBatch()
                }
            }
        }
    } else {
        // Show a loading indicator while fetching the products
        LoadingView(modifier = modifier)
    }

}

@Preview(showBackground = true)
@Composable
fun ProductCardPrev() {
    val product =  Product(1,"Jacket", 100.99, description = "Product",
        "https://i.pravatar.cc/", "Men", null)
    ProductCard(product, {}, Modifier)

}

@Composable
fun ProductCard(product: Product, onProductClicked: () -> Unit, modifier: Modifier) {
    Box(
        modifier = modifier
            .height(250.dp)
            .width(150.dp)
            .background(colorResource(id = R.color.neutral_200))
            .clickable {
                onProductClicked()
            }
    ) {
        Column {
            // Product image
            Image(
                painter = rememberAsyncImagePainter(
                    model = remember {
                        product.image
                    },
                    placeholder = painterResource(R.drawable.image_placeholder),
                    error = painterResource(R.drawable.icon_error)
                ),
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(4.dp),
                contentScale = ContentScale.Crop // ContentScale.Crop makes sure the image fills the entire box
            )

            Spacer(modifier = modifier.height(8.dp))

            // Product details
            Column(modifier = modifier.padding(4.dp)) {
                Text(
                    text = product.title,
                    style = AppTypography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    color = colorResource(id = R.color.neutral_500)
                )

                Spacer(modifier = modifier.height(4.dp))

                // Shopping cart button
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$" + product.price,
                        style = AppTypography.bodySmall,
                        color = Color.Black
                    )
                    
                    IconButton(onClick = {
                        // add to cart functionality
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_cart),
                            contentDescription = null,
                            modifier = modifier
                                .width(20.dp)
                                .height(20.dp),
                            tint = Color.Black
                        )
                    }
                }
            }
        }
    }
}