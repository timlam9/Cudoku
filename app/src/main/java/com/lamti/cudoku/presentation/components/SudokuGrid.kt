package com.lamti.cudoku.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lamti.cudoku.domain.Solver.Companion.GRID_SIZE
import com.lamti.cudoku.domain.regionIndex

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
            val modifier = Modifier.getModifier(isLoading, box, boxIndexClicked, index, onClick)

            SudokuBox(
                modifier = modifier,
                isBoxClicked = index == boxIndexClicked,
                isOddBox = regionIndex(index, GRID_SIZE).mod(2) == 0,
                box = box,
            )
        }
    }
}

private fun Modifier.getModifier(
    isLoading: Boolean,
    box: SudokuDataBox,
    boxIndexClicked: Int,
    index: Int,
    onClick: (index: Int) -> Unit
): Modifier {
    return if (isLoading || box.isInitialValue) this
    else clickable(onClick = { if (boxIndexClicked == index) onClick(-1) else onClick(index) })
}
