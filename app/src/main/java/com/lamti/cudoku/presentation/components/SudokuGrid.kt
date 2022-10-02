package com.lamti.cudoku.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.lamti.cudoku.domain.Solver.Companion.GRID_SIZE
import com.lamti.cudoku.domain.rowIndex

private const val GRID_PADDING = 2
private const val CELL_PADDING = 2
private const val ELEVATION = 2
private const val ASPECT_RATIO = 1f
private const val TEXT_FONT_SIZE = 14
const val EMPTY_VALUE = 0

@Composable
fun SudokuGrid(boxes: List<UiBox>, boxSize: Int) {
    val gridState = rememberLazyListState()
    LazyVerticalGrid(
        cells = GridCells.Adaptive(boxSize.dp),
        contentPadding = PaddingValues(GRID_PADDING.dp),
        state = gridState
    ) {
        itemsIndexed(boxes) { index, box ->
            val extraPaddingTop = if (rowIndex(index, GRID_SIZE).mod(3) == 0 && rowIndex(index, GRID_SIZE) != 0) {
                20.dp
            } else 0.dp

//            val linePadding = if (columnIndex(index, GRID_SIZE).mod(3) == 0 && columnIndex(index, GRID_SIZE) != 0) {
////                Divider(Modifier.width(16.dp), thickness = 16.dp, color = Color.Red)
//                20.dp
//            } else 0.dp

            SudokuBox(modifier = Modifier.padding(top = extraPaddingTop), box = box)
        }
    }
}

@Composable
fun SudokuBox(modifier: Modifier = Modifier, box: UiBox) {
    SudokuBox(
        modifier = modifier,
        text = if (box.value != EMPTY_VALUE) box.value.toString() else "",
        textColor = box.color,
    )
}

@Composable
fun SudokuBox(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colors.background,
    textColor: Color = MaterialTheme.colors.onBackground,
    showText: Boolean = true
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

fun Cell.toUiBox() = UiBox(value = value, color = if (isInitialValue) Color.Black else Color.Blue, selected = false)
