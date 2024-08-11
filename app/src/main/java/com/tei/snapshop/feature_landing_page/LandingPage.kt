package com.tei.snapshop.feature_landing_page

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.tei.snapshop.R
import com.tei.snapshop.feature_landing_page.compose.BottomNavigationItem
import com.tei.snapshop.ui.HomeNavGraph
import com.tei.snapshop.ui.theme.AppTypography

/**
 * Landing page base class
 * Created by Eniola Ipoola on 26/06/2024.
 * Copyright (c). All rights reserved
 */

@Composable
fun LandingPage() {
    LandingPageContent()
}

@Composable
fun LandingPageContent() {
    val navController = rememberNavController()
    var navigationSelectedItem by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                BottomNavigationItem().bottomNavigationItems().forEachIndexed {
                    index, navigationItem ->
                    NavigationBarItem(selected = index == navigationSelectedItem  ,
                        onClick = {
                                  navigationSelectedItem = index
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(navigationItem.icon, contentDescription = navigationItem.label)
                        },
                        label = { Text(navigationItem.label) }
                    )
                }
            }
        },
        topBar = { TopBarCompose() },
        modifier = Modifier
    ) { innerPadding ->
        HomeNavGraph(navController = navController, innerPadding) {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarCompose() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = AppTypography.titleLarge,
                color = colorResource(id = R.color.color_primary)
            )
        },
        modifier = Modifier,
        colors = topAppBarColors(
            containerColor = Color.White
        )
    )

}