package com.example.android_gameapplication.ui.layoutComponents

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_gameapplication.R
import com.example.android_gameapplication.ui.detailpage.DetailpageOverview
import com.example.android_gameapplication.ui.searchpage.SearchpageOverview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameApp() {

    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                {
                    val isStartDestination =
                        currentBackStackEntry?.destination?.route == Destinations.Start.name
                    if (isStartDestination) {
                        IconButton(onClick = {
                            /* show menu*/
                        }) {
                            Icon(Icons.Outlined.Menu, contentDescription = "Localized description")
                        }
                    } else {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(
                                Icons.Outlined.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    }

                },
                when (currentBackStackEntry?.destination?.route) {
                    Destinations.Search.name -> R.string.searchpage_title
                    Destinations.DetailPage.name -> R.string.detailpage_title
                    else -> R.string.app_name
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                { navController.popBackStack(Destinations.Start.name, false) },
                { navController.navigate(Destinations.Search.name) })
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destinations.Start.name,
            Modifier.padding(innerPadding)
        ) {
            composable(route = Destinations.Start.name) {
                StartScreen(
                    onCarousel = { gameId -> navController.navigate("${Destinations.DetailPage.name}/${gameId}") })
            }
            composable(route = Destinations.Search.name) {
                SearchpageOverview(onListItem = { gameId ->
                    navController.navigate(
                        "${Destinations.DetailPage.name}/${gameId}"
                    )
                })
            }

            composable("${Destinations.DetailPage.name}/{id}") {
                DetailpageOverview(gameId = it.arguments?.getString("id")?.toInt() ?: 0)
            }
        }
    }
}
