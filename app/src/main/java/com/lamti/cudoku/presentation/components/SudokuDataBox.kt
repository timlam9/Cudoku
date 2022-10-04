package com.lamti.cudoku.presentation.components

import androidx.compose.ui.graphics.Color

data class SudokuDataBox(
    val value: Int,
    val color: Color,
    val selected: Boolean,
    val isInitialValue: Boolean
)
