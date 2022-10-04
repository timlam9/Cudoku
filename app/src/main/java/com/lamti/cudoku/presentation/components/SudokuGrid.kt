package com.lamti.cudoku.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lamti.cudoku.domain.Solver.Companion.GRID_SIZE
import com.lamti.cudoku.domain.rowIndex

@Composable
fun SudokuGrid(
    boxes: List<SudokuDataBox>,
    boxSize: Int,
    isLoading: Boolean,
    boxIndexClicked: Int,
    onClick: (index: Int) -> Unit
) {
    val gridState = rememberLazyListState()

    LazyVerticalGrid(
        cells = GridCells.Adaptive(boxSize.dp),
        contentPadding = PaddingValues(GRID_PADDING.dp),
        state = gridState
    ) {
        itemsIndexed(
            items = boxes,
        ) { index, box ->
            val extraPaddingTop = if (rowIndex(index, GRID_SIZE).mod(3) == 0 && rowIndex(index, GRID_SIZE) != 0) {
                20.dp
            } else 0.dp

            val modifier = if (!isLoading && !box.isInitialValue)
                Modifier
                    .padding(top = extraPaddingTop)
                    .clickable(onClick = { if (boxIndexClicked == index) onClick(-1) else onClick(index) })
            else
                Modifier
                    .padding(top = extraPaddingTop)

            SudokuBox(
                modifier = modifier,
                isBoxClicked = index == boxIndexClicked,
                box = box,
            )
        }
    }
}