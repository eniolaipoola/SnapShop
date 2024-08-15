package com.tei.snapshop.feature_products

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tei.snapshop.data.di.DispatcherProvider
import com.tei.snapshop.data.network.NoInternetException
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

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    private var currentOffset = 0
    private val limit = 50

    val products: StateFlow<List<Product>> get() = _products

    fun onSearchQueryChanged(searchText: String) {
        searchQuery.value = searchText
        //perform db search
    }

    fun fetchInitialProducts() {
        viewModelScope.launch(dispatcher.io) {
            try {
                val initialProductList = repository.getAllProducts(limit)
                _products.value = initialProductList
                currentOffset = initialProductList.size
            } catch (error: NoInternetException) {
                _error.value = error.message
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }


    fun loadNextBatch() {
        viewModelScope.launch(dispatcher.io) {
            try {
                val nextBatch = repository.loadNextBatch(currentOffset, limit)
                _products.value += nextBatch
                currentOffset += nextBatch.size
            } catch (e: NoInternetException) {
                _error.value = e.message
            }
        }
    }

}