package com.example.android_gameapplication.ui.homepage

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
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
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(8.dp),
        text = title,
        fontSize = 28.sp
    )

    Carousel(
        gamesList = gamesList,
        onCarousel = onCarousel
    )

    FilledTonalButton(
        modifier = modifier
            .padding(bottom = 10.dp),

        onClick = { /*Action*/ },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("More popular games", fontSize = 14.sp)
            Spacer(modifier = modifier.width(8.dp)) // Voeg 8dp witruimte toe tussen de tekst en de pijl
            Icon(imageVector = Icons.Outlined.ArrowForward, contentDescription = "Arrow Icon")
        }
    }
}
