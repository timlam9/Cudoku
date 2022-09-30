package com.lamti.cudoku.domain

import kotlin.math.sqrt

fun List<Cell>.splitToRows() = chunked(sqrt(size.toDouble()).toInt())

fun List<Cell>.splitToColumns(n: Int): List<List<Cell>> = splitToRows().run {
    (0 until n).map { index ->
        map { row -> row[index] }
    }
}

fun List<Cell>.splitToRegions(n: Int): List<List<Cell>> {
    val regions: List<List<Cell>> = mapIndexed { index, cell -> squareIndex(index, n) to cell }
        .groupBy { it.first }
        .map { it.value.map { pair -> pair.second } }

    regions.forEach { println(it) }
    return regions
}

private fun squareIndex(index: Int, n: Int): Int {
    val squareColumn = (index % n) / 3
    val squareRow = (index / n) / 3
    return 3 * squareRow + squareColumn
}
