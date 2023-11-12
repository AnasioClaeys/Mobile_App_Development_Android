package com.example.android_gameapplication.ui.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.android_gameapplication.R
import com.example.android_gameapplication.ui.model.Game

@Composable
fun GameCard(game: Game, onCarousel: (Int) -> Unit, modifier:Modifier=Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .height(200.dp)
            .clickable(onClick = {onCarousel(game.id)})
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .width(150.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.f2dtyp_wwaabdst),
                contentDescription = game.title,
                modifier = modifier
                    .fillMaxWidth()
                    .height(140.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = game.title,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun Carousel(gamesList: List<Game>, onCarousel: (Int) -> Unit) {
    LazyRow() {
        items(gamesList) { game ->
            GameCard(
                game = game,
                onCarousel = onCarousel
            )
        }
    }
}

/*@Preview
@Composable
fun CardPrev() {
    Carousel(gamesList)
}*/
