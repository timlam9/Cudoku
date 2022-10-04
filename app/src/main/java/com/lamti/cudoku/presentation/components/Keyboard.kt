package com.lamti.cudoku.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Keyboard(onClick: (number: Int) -> Unit, keySize: Int) {
    val gridState = rememberLazyListState()

    LazyVerticalGrid(
        cells = GridCells.Adaptive(keySize.dp),
        contentPadding = PaddingValues((GRID_PADDING * 10).dp),
        state = gridState
    ) {
        items((0..9).toList()) { number ->
            KeyboardNumber(text = number, onClick = { onClick(number) })
        }
    }
}

@Composable
fun KeyboardNumber(
    modifier: Modifier = Modifier,
    text: Int,
    color: Color = Color.White,
    textColor: Color = Color.Blue,
    onClick: () -> Unit
) {
    Card(
        backgroundColor = color,
        modifier = modifier
            .padding(CELL_PADDING.dp)
            .aspectRatio(ASPECT_RATIO)
            .clickable { onClick() },
        elevation = ELEVATION.dp,
    ) {
        Text(
            text = text.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = TEXT_FONT_SIZE.sp,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.wrapContentSize(Alignment.Center)
        )
    }
}
