package com.lamti.cudoku.presentation.components

import android.os.Parcelable
import com.lamti.cudoku.domain.Cell
import kotlinx.parcelize.Parcelize

@Parcelize
data class SudokuDataBox(
    val value: Int,
    val boxColor: BoxColor,
    val selected: Boolean,
    val isInitialValue: Boolean
): Parcelable

@Parcelize
enum class BoxColor : Parcelable {
    White,
    Black,
    LightGray,
    Blue
}

fun List<Cell>.toSudokuDataBoxes() = map { it.toSudokuDataBox() }

fun Cell.toSudokuDataBox() = SudokuDataBox(
    value = value,
    boxColor = if (isInitialValue) BoxColor.Black else BoxColor.Blue,
    selected = false,
    isInitialValue = isInitialValue
)

fun List<SudokuDataBox>.toCells() = map { it.toCell() }

fun SudokuDataBox.toCell() = Cell(value = value, isInitialValue = isInitialValue)
