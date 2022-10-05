package com.lamti.cudoku.domain

import kotlin.math.sqrt

fun List<Cell>.splitToRows() = chunked(sqrt(size.toDouble()).toInt())

fun List<Cell>.splitToColumns(n: Int): List<List<Cell>> = splitToRows().run {
    (0 until n).map { index ->
        map { row -> row[index] }
    }
}

fun List<Cell>.splitToRegions(n: Int): List<List<Cell>> = mapIndexed { index, cell -> regionIndex(index, n) to cell }
    .groupBy { it.first }
    .map { it.value.map { pair -> pair.second } }

fun regionIndex(index: Int, n: Int): Int {
    val squareColumn = (index % n) / 3
    val squareRow = (index / n) / 3
    return 3 * squareRow + squareColumn
}

fun rowIndex(index: Int, n: Int): Int = index.div(n)

fun columnIndex(index: Int, n: Int): Int = index.mod(n)

fun Int.isInDiagonalRegion(): Boolean = regionIndex(this, Solver.GRID_SIZE).let { regionIndex ->
    regionIndex == 0 || regionIndex == 4 || regionIndex == 8
}
