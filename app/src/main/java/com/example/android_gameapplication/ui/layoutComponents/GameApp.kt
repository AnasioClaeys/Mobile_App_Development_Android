package com.example.android_gameapplication.ui.layoutComponents

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_gameapplication.R
import com.example.android_gameapplication.ui.detailpage.DetailpageOverview
import com.example.android_gameapplication.ui.listpagePopularGamesAllTime.ListpageOverviewPopularGamesAllTime
import com.example.android_gameapplication.ui.listpagePopularGamesThisYear.ListPageOverviewPopularGamesThisYear
import com.example.android_gameapplication.ui.searchpage.SearchpageOverview

@Composable
fun GameApp(navController: NavHostController = rememberNavController()) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    val onHome: () -> Unit = {
        navController.popBackStack(Destinations.Start.name, false)
    }

    val onSearch: () -> Unit = {
        navController.navigate(Destinations.Search.name) { launchSingleTop = true }
    }

    val onDetail: (Int) -> Unit = { gameId ->
        navController.navigate("${Destinations.DetailPage.name}/${gameId}") {
            launchSingleTop = true
        }
    }

    val onListPopularGamesAllTime: () -> Unit = {
        navController.navigate(Destinations.ListPagePopularGamesAllTime.name) {
            launchSingleTop = true
        }
    }

    val onListPopularGamesOfThisYear: () -> Unit = {
        navController.navigate(Destinations.ListPagePopularGamesOfThisYear.name) {
            launchSingleTop = true
        }
    }



    Scaffold(
        topBar = {
            TopAppBar(
                {
                    val isStartDestination =
                        currentBackStackEntry?.destination?.route == Destinations.Start.name

                    if (!isStartDestination)
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(
                                Icons.Outlined.ArrowBack,
                                contentDescription = stringResource(R.string.go_back)
                            )
                        }
                },

                title = R.string.app_name

            )
        },
        bottomBar = {
            BottomAppBar(
                onHome = onHome,
                onSearch = onSearch,
                currentBackStackEntry = currentBackStackEntry?.destination?.route
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
                    onCarousel = onDetail,
                    onListPopularGamesAllTime = onListPopularGamesAllTime,
                    onListPopularGamesOfThisYear = onListPopularGamesOfThisYear
                )
            }
            composable(route = Destinations.Search.name) {
                SearchpageOverview(onListItem = onDetail)
            }
            composable(route = Destinations.ListPagePopularGamesAllTime.name) {
                ListpageOverviewPopularGamesAllTime(onListItem = onDetail)
            }
            composable(route = Destinations.ListPagePopularGamesOfThisYear.name) {
                ListPageOverviewPopularGamesThisYear(onListItem = onDetail)
            }
            composable("${Destinations.DetailPage.name}/{id}") {
                DetailpageOverview(gameId = it.arguments?.getString("id")?.toInt() ?: 0)
            }
        }
    }
}
