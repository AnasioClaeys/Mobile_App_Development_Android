package com.example.android_gameapplication.ui.searchpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.android_gameapplication.R
import com.example.android_gameapplication.ui.model.Game


@Composable
fun GameListItem(game: Game, onListitem: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .clickable(onClick = { onListitem(game.id) }),
        shape = RoundedCornerShape(15.dp),
//        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.f2dtyp_wwaabdst),
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .clip(
                        RoundedCornerShape(16.dp)
                    )
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .weight(1f)
            ) {
                Text(
                    text = game.title,
                    fontSize = 20.sp,
                    color = Color(0xFF2b2b2b)
                )
            }
            IconButton(
                onClick = { onListitem(game.id) }
            ) {
                Icon(imageVector = Icons.Outlined.ArrowForward, contentDescription = "Arrow Icon")
            }
        }
    }
}

@Composable
fun GamesList(gamesList: List<Game>, onListItem: (Int) -> Unit) {
    LazyColumn {
        items(gamesList) { game ->
            GameListItem(
                game = game,
                onListitem = onListItem
            )
        }
    }
}


/*@Preview
@Composable
fun GameListPreview() {
    GamesList(gamesList = gamesList)
}*/
