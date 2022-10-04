package com.lamti.cudoku.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.lamti.cudoku.R

@Composable
fun SudokuScreen(
    board: List<SudokuDataBox>,
    boxSize: Int,
    keySize: Int,
    isLoading: Boolean,
    isSolved: Boolean,
    isBoxClicked: Boolean,
    boxIndexClicked: Int,
    onSolveClick: () -> Unit,
    onBoxClick: (index: Int) -> Unit,
    onKeyboardNumberClick: (number: Int) -> Unit,
) {
    val buttonText = if (isSolved) stringResource(R.string.play_again) else stringResource(R.string.solve)

    Column(modifier = Modifier.fillMaxSize()) {
        SudokuGrid(
            boxes = board,
            boxSize = boxSize,
            isLoading = isLoading,
            boxIndexClicked = boxIndexClicked,
            onClick = onBoxClick
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.animation.AnimatedVisibility(visible = isLoading) {
                CircularProgressIndicator()
            }
            androidx.compose.animation.AnimatedVisibility(visible = !isLoading && !isBoxClicked) {
                TextButton(text = buttonText, onClick = onSolveClick)
            }
            androidx.compose.animation.AnimatedVisibility(visible = isBoxClicked) {
                Keyboard(onClick = onKeyboardNumberClick, keySize = keySize)
            }
        }
    }
}
