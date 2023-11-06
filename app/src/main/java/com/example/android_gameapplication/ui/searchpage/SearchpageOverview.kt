package com.example.android_gameapplication.ui.searchpage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_gameapplication.data.gamesList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchpageOverview() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth())
        
        GamesList(gamesList = gamesList)

    }
}

@Preview
@Composable
fun SearchpageOverviewPreview() {
    SearchpageOverview()
}
