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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_gameapplication.R

enum class Destinations {
    Start,
    Search
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameApp() {

    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(
        topBar = {
            TopAppBar({
                val isStartDestination = currentBackStackEntry?.destination?.route == Destinations.Start.name
                if(isStartDestination){
                    IconButton(onClick = {
                        /* show menu*/
                    }) {
                        Icon(Icons.Outlined.Menu, contentDescription = "Localized description")
                    }
                } else{
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Outlined.ArrowBack, contentDescription = "Localized description")
                    }
                }

            },
                when(currentBackStackEntry?.destination?.route){
                    Destinations.Search.name-> R.string.search_page_title
                    else -> R.string.app_title
            } )
        },
        bottomBar = {
            BottomAppBar({ navController.popBackStack(Destinations.Start.name,false) }, { navController.navigate(Destinations.Search.name) })
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destinations.Start.name,
            Modifier.padding(innerPadding)
        ) {
            composable(route = Destinations.Start.name) {
                StartScreen()
            }
            composable(route = Destinations.Search.name) {
                Text(text = "Search page", fontSize = 28.sp)
            }
        }
    }
}