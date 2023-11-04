package com.example.android_gameapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_gameapplication.data.gamesList
import com.example.android_gameapplication.ui.model.Game

@Composable
fun GameCard(game: Game, modifier: Modifier) {
    Card(
        modifier = modifier
            .height(200.dp) // Verhoog de hoogte van de kaart
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .width(150.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.f2dtyp_wwaabdst),
                contentDescription = game.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp), // Verhoog de hoogte van de afbeelding
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center, // Plaats de tekst verticaal in het midden
                        horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = game.title,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center // Horizontale uitlijning in het midden
                )
            }
        }
    }
}

@Composable
fun LazyRowShower2(gamesList: List<Game>, modifier: Modifier) {
    LazyRow(modifier = modifier) {
        items(gamesList) { game ->
            GameCard(game = game, modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview
@Composable
fun CardPrev() {
    LazyRowShower2(gamesList, Modifier.fillMaxSize())
}
