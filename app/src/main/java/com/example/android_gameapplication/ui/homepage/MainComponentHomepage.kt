package com.example.android_gameapplication.ui.homepage

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.android_gameapplication.model.Game

@Composable
fun MainComponentHomepage(
    title: String,
    gamesList: List<Game>,
    onCarousel: (Int) -> Unit,
    onList: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
//        modifier = modifier.padding(top=16.dp),
        text = title,
        style = MaterialTheme.typography.headlineMedium
    )

    Carousel(
        gamesList = gamesList,
        onCarousel = onCarousel
    )

    FilledTonalButton(
        onClick = { onList() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("More games", style = MaterialTheme.typography.labelLarge)
            Icon(imageVector = Icons.Outlined.ArrowForward, contentDescription = "Arrow Icon")
        }
    }
}
