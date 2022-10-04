package com.lamti.cudoku.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lamti.cudoku.domain.Cell

@Composable
fun SudokuBox(
    modifier: Modifier = Modifier,
    isBoxClicked: Boolean,
    box: UiBox,
) {
    SudokuBox(
        modifier = modifier,
        text = if (box.value != EMPTY_VALUE) box.value.toString() else "",
        color = if (isBoxClicked) Color.Blue else Color.White,
        textColor = if (isBoxClicked) Color.White else box.color
    )
}

@Composable
fun SudokuBox(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colors.background,
    textColor: Color = MaterialTheme.colors.onBackground,
    showText: Boolean = true,
) {
    Card(
        backgroundColor = color,
        modifier = modifier
            .padding(CELL_PADDING.dp)
            .aspectRatio(ASPECT_RATIO),
        elevation = ELEVATION.dp,
    ) {
        AnimatedVisibility(visible = showText) {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                fontSize = TEXT_FONT_SIZE.sp,
                color = textColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentSize(Alignment.Center)
            )
        }
    }
}

fun List<Cell>.toUiBoxes() = map { it.toUiBox() }

fun Cell.toUiBox() = UiBox(
    value = value,
    color = if (isInitialValue) Color.Black else Color.Blue,
    selected = false,
    isInitialValue = isInitialValue
)

fun List<UiBox>.toCells() = map { it.toCell() }

fun UiBox.toCell() = Cell(value = value, isInitialValue = isInitialValue)
