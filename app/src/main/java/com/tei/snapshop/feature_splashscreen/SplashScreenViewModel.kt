package com.tei.snapshop.feature_splashscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tei.snapshop.ui.NavScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Class Description
 * Created by Eniola Ipoola on 05/06/2024.
 * Copyright (c). All rights reserved
 */
@HiltViewModel
class SplashScreenViewModel @Inject constructor(): ViewModel()
{
    private val _isLoading : MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination : MutableState<String> = mutableStateOf(NavScreen.OnboardingScreen.route)
    val startDestination : State<String> = _startDestination

    init {
        viewModelScope.launch {
            // there is a logged in user
            _startDestination.value = NavScreen.OnboardingScreen.route
        }

        _isLoading.value = true
    }
}