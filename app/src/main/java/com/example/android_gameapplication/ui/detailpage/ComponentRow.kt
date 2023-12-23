package com.example.android_gameapplication.ui.detailpage

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_gameapplication.R

@Composable
fun ComponentRow(title: String, component: List<String>, modifier: Modifier = Modifier) {
    val viewModel: DetailpageOverviewViewModel =
        viewModel(factory = DetailpageOverviewViewModel.Factory)
    val expanded =
        viewModel.detailpageOverviewState.collectAsState().value.expandedStates[title] ?: false
    Column(
        modifier = modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium,
                ),
            ),
    ) {
        Row {
            Text(
                modifier = modifier
                    .padding(8.dp)
                    .padding(start = 10.dp),
                text = title,
                fontSize = 22.sp,
            )
            IconButton(
                onClick = { viewModel.toggleExpanded(title) },
            ) {
                Icon(

                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = stringResource(R.string.expandable_icon),
                )
            }
        }

        if (!expanded) {
            LazyRow(
                modifier = modifier.padding(start = 10.dp),
                content = {
                    component.forEach { component ->
                        item {
                            FilledTonalButton(
                                onClick = {
                                    // Handle button for expansion app
                                },
                                content = {
                                    Text(text = component)
                                },
                                modifier = modifier.padding(start = 8.dp, end = 8.dp),
                            )
                        }
                    }
                },
            )
        }
    }
}
