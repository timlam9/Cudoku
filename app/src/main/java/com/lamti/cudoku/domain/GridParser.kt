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

const val initialGrid = "5,3,,,7,,,,\n" +
        "6,,,1,9,5,,,\n" +
        ",9,8,,,,,6,\n" +
        "8,,,,6,,,,3\n" +
        "4,,,8,,3,,,1\n" +
        "7,,,,2,,,,6\n" +
        ",6,,,,,2,8,\n" +
        ",,,4,1,9,,,5\n" +
        ",,,,8,,,7,9"
