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

    val onHome: () -> Unit = {
        navController.popBackStack(Destinations.Start.name, false)
    }

    val onSearch: () -> Unit = {
        navController.navigate(Destinations.Search.name)
    }

    val onDetail: (Int) -> Unit = { gameId ->
        navController.navigate("${Destinations.DetailPage.name}/${gameId}")
    }

//    val canNavigateBack = navController.previousBackStackEntry != null
//    val navigateUp: () -> Unit = { navController.navigateUp() }

//    val currentScreenTitle = Destinations.valueOf(
//        currentBackStackEntry?.destination?.route ?: Destinations.Start.name,
//    ).title

    Scaffold(
        topBar = {
            TopAppBar(
                {
//                    canNavigateBack = canNavigateBack,
//                    navigateUp = navigateUp,
//                    currentScreenTitle = currentScreenTitle,

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
                onHome = onHome,
                onSearch = onSearch
                )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destinations.Start.name,
            Modifier.padding(innerPadding)
        ) {
            composable(route = Destinations.Start.name) {
                StartScreen(
                    onCarousel = onDetail)
            }
            composable(route = Destinations.Search.name) {
                SearchpageOverview(onListItem = onDetail)
            }

            composable("${Destinations.DetailPage.name}/{id}") {
                DetailpageOverview(gameId = it.arguments?.getString("id")?.toInt() ?: 0)
            }
        }
    }
}
