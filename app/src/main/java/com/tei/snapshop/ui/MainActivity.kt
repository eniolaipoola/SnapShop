/*
 * Copyright (c)
 * Class Description
 * Created by Eniola Ipoola on 2024.
 * Copyright (c) $year. All rights reserved
 *
 */

package com.tei.snapshop.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.tei.snapshop.feature_splashscreen.SplashScreenViewModel
import com.tei.snapshop.ui.theme.SnapShopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val splashViewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }

        super.onCreate(savedInstanceState)

        setContent {
            SnapShopTheme {
                val screen by splashViewModel.startDestination
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    AppMainScreen(screen)
                }
            }
        }
    }
}
