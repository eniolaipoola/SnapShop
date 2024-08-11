package com.tei.snapshop.feature_products

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tei.snapshop.data.di.DispatcherProvider
import com.tei.snapshop.feature_authentication.sign_in.data.repository.SignInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Class Description
 * Created by Eniola Ipoola on 10/08/2024.
 * Copyright (c). All rights reserved
 */

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val dispatcher: DispatcherProvider,
    private val repository: SignInRepository
): ViewModel() {

    var searchQuery = mutableStateOf("")
        private set
    fun onSearchQueryChanged(searchText: String) {
        searchQuery.value = searchText
        //perform db search
    }
}