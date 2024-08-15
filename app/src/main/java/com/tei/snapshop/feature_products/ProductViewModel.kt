package com.tei.snapshop.feature_products

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tei.snapshop.data.di.DispatcherProvider
import com.tei.snapshop.feature_products.data.Product
import com.tei.snapshop.feature_products.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Class Description
 * Created by Eniola Ipoola on 10/08/2024.
 * Copyright (c). All rights reserved
 */

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val dispatcher: DispatcherProvider,
    private val repository: ProductRepository
): ViewModel() {

    var searchQuery = mutableStateOf("")
        private set

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    fun onSearchQueryChanged(searchText: String) {
        searchQuery.value = searchText
        //perform db search
    }

    fun fetchProducts() {
        viewModelScope.launch(dispatcher.io) {
            _products.value = repository.getAllProducts()
        }
    }

}