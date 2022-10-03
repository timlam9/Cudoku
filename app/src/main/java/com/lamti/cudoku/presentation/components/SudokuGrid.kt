package com.lamti.cudoku.presentation.components

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
