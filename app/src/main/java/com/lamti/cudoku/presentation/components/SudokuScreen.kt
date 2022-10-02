package com.lamti.cudoku.presentation.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.lamti.cudoku.R

@Composable
fun SudokuScreen(board: List<UiBox>, boxSize: Int, isLoading: Boolean, onSolveClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        SudokuGrid(board, boxSize)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.animation.AnimatedVisibility(visible = isLoading) {
                CircularProgressIndicator()
            }
            androidx.compose.animation.AnimatedVisibility(visible = !isLoading) {
                TextButton(text = stringResource(R.string.solve), onClick = onSolveClick)
            }
        }
    }
}


@Composable
fun TextButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(backgroundColor = Color.LightGray)
    ) {
        Text(text, color = MaterialTheme.colors.onBackground)
    }
}
