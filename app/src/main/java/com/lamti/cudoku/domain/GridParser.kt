package com.lamti.cudoku.domain

import com.lamti.cudoku.presentation.components.EMPTY_VALUE

@OptIn(ExperimentalStdlibApi::class)
fun createEmptyGrid(n: Int): List<Cell> = buildList {
    repeat(n * n) {
        add(Cell(EMPTY_VALUE, false))
    }
}

@OptIn(ExperimentalStdlibApi::class)
fun createGridFrom(text: String): List<Cell> = buildList {
    text.split("\n").forEach { line ->
        line.split(",").forEach { value ->
            if (value.isEmpty()) {
                add(Cell(EMPTY_VALUE, false))
            } else {
                add(Cell(value.toInt(), true))
            }
        }
    }
}
