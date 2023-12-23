package com.example.android_gameapplication.ui.layoutComponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_gameapplication.R
import com.example.android_gameapplication.ui.detailpage.DetailpageOverview
import com.example.android_gameapplication.ui.layoutComponents.LandscapeComponents.NavigationRailAppBar
import com.example.android_gameapplication.ui.listpagePopularGamesAllTime.ListpageOverviewPopularGamesAllTime
import com.example.android_gameapplication.ui.listpagePopularGamesThisYear.ListPageOverviewPopularGamesThisYear
import com.example.android_gameapplication.ui.searchpage.SearchpageOverview

@Composable
fun GameApp(
    navController: NavHostController = rememberNavController(),
    windowSize: WindowWidthSizeClass,
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val isStartDestination =
        currentBackStackEntry?.destination?.route == Destinations.Start.name

    var selectedGameId by remember { mutableStateOf<Int?>(null) }
    val onCloseDetailPage: () -> Unit = {
        selectedGameId = null
    }

    val onHome: () -> Unit = {
        navController.popBackStack(Destinations.Start.name, false)
    }

    val onSearch: () -> Unit = {
        navController.navigate(Destinations.Search.name) { launchSingleTop = true }
    }

    val onDetail: (Int) -> Unit = { gameId ->
        selectedGameId = gameId
        if (windowSize == WindowWidthSizeClass.Compact || windowSize == WindowWidthSizeClass.Medium) {
            navController.navigate("${Destinations.DetailPage.name}/$gameId") {
                launchSingleTop = true
            }
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

    val navigationType: GameAppNavigationType
    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = GameAppNavigationType.BOTTOM_NAVIGATION
        }

        WindowWidthSizeClass.Medium -> {
            navigationType = GameAppNavigationType.NAVIGATION_RAIL
        }

        WindowWidthSizeClass.Expanded -> {
            navigationType = GameAppNavigationType.PERMANENT_NAVIGATION_DRAWER
        }

        else -> {
            navigationType = GameAppNavigationType.BOTTOM_NAVIGATION
        }
    }

    Scaffold(
        topBar = {
            if (navigationType == GameAppNavigationType.BOTTOM_NAVIGATION) {
                TopAppBar(
                    {
                        val isStartDestination =
                            currentBackStackEntry?.destination?.route == Destinations.Start.name

                        if (!isStartDestination) {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(
                                    Icons.Outlined.ArrowBack,
                                    contentDescription = stringResource(R.string.go_back),
                                )
                            }
                        }
                    },

                    title = R.string.app_name,

                )
            }
        },
        bottomBar = {
            if (navigationType == GameAppNavigationType.BOTTOM_NAVIGATION) {
                BottomAppBar(
                    onHome = onHome,
                    onSearch = onSearch,
                    currentBackStackEntry = currentBackStackEntry?.destination?.route,
                )
            }
        },
    ) { innerPadding ->
        Row {
            if (navigationType == GameAppNavigationType.NAVIGATION_RAIL || navigationType == GameAppNavigationType.PERMANENT_NAVIGATION_DRAWER) {
                NavigationRailAppBar(
                    onItemSelected = { selectedItem ->
                        when (selectedItem) {
                            0 -> {
                                if (!isStartDestination) {
                                    navController.popBackStack()
                                }
                            }
                            1 -> {
                                onHome()
                            }
                            2 -> {
                                onSearch()
                            }
                        }
                    },
                    isStartDestination = isStartDestination,
                    showCloseIcon = windowSize == WindowWidthSizeClass.Expanded && selectedGameId != null,
                    onClose = onCloseDetailPage,
                )
            }

            NavHost(
                navController = navController,
                startDestination = Destinations.Start.name,
                if (windowSize == WindowWidthSizeClass.Expanded) Modifier.weight(1f) else Modifier.padding(innerPadding),
            ) {
                composable(route = Destinations.Start.name) {
                    StartScreen(
                        onCarousel = onDetail,
                        onListPopularGamesAllTime = onListPopularGamesAllTime,
                        onListPopularGamesOfThisYear = onListPopularGamesOfThisYear,
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

            if ((windowSize == WindowWidthSizeClass.Expanded) && selectedGameId != null) {
                DetailpageOverview(gameId = selectedGameId!!, Modifier.weight(1f))
            }
        }
    }
}
