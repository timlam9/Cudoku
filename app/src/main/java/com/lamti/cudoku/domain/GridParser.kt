package com.lamti.cudoku.domain

@OptIn(ExperimentalStdlibApi::class)
fun createEmptyGrid(n: Int): List<Cell> = buildList {
    repeat(n * n) {
        add(Cell(0))
    }
}

@OptIn(ExperimentalStdlibApi::class)
fun createGridFrom(text: String): List<Cell> = buildList {
    text.split("\n").forEach { line ->
        line.split(",").forEach { value ->
            if (value.isEmpty()) {
                add(Cell(0))
            } else {
                add(Cell(value.toInt()))
            }
        }
    }
}
