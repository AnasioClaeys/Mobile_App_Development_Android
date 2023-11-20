package com.example.android_gameapplication.ui.detailpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_gameapplication.R
import com.example.android_gameapplication.data.gamesList
import com.example.android_gameapplication.ui.ViewModel.GameViewModel
import com.example.android_gameapplication.model.Game

@Composable
fun DetailpageOverview(gameId: Int, modifier: Modifier = Modifier,) {
    val viewModel: GameViewModel = viewModel()

    val game = viewModel.getGameById(gameId)
//    val game = Game.getGameById(gameId)

    LazyColumn(
        modifier = modifier.fillMaxWidth().padding(bottom = 16.dp)
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.f2dtyp_wwaabdst),
                contentDescription = game.name,
                modifier = modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = modifier.padding(8.dp),
                text = "${game.name}",
                fontSize = 28.sp
            )

            Text(
                modifier = modifier
                    .padding(start = 8.dp),
                text = "Summary:",
                fontSize = 22.sp
            )

            Text(
                modifier = modifier.padding(start = 8.dp),
                text = "blaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                fontSize = 18.sp
            )

            ComponentRow("Genres", game.genres)

            ComponentRow("Platforms", game.platforms)


            Text(
                modifier = modifier.padding(8.dp),
                text = "Release date:",
                fontSize = 22.sp
            )

            FilledTonalButton(
                onClick = {
                    // Handle button click here if needed
                },
                content = {
                    Text(text = "${game.released}")
                },
                modifier = modifier.padding(start = 8.dp)
            )
        }

    }
}


//@Preview
//@Composable
//fun DetailPageOverviewPreview() {
//    DetailpageOverview(gamesList[0])
//}
