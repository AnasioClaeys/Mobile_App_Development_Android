package com.example.android_gameapplication.ui.detailpage

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.android_gameapplication.R
import com.example.android_gameapplication.model.Game
import com.example.android_gameapplication.ui.ViewModel.GameViewModel

@Composable
fun Detailpage(game: Game, modifier: Modifier = Modifier) {
    val viewModel: GameViewModel = viewModel(factory = GameViewModel.Factory)


    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        item {
            AsyncImage(
                model = game.backgroundImage, contentDescription = game.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )


            Text(
                modifier = modifier.padding(8.dp),
                text = "${game.name}",
                fontSize = 28.sp
            )


            ComponentRow(stringResource(R.string.detail_title_genres), viewModel.listToString(game.genres))

            ComponentRow(stringResource(R.string.detail_title_platforms), viewModel.listToString(game.platforms))

            ComponentRow(title = stringResource(R.string.detail_title_release_date), component = game.released)

            ComponentRow(title = stringResource(R.string.detail_title_average_playtime), component = stringResource(
                R.string.detail_item_playtime_hours, game.playtime
            )
            )
        }

    }
}
