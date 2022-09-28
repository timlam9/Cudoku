package com.lamti.cudoku.domain

import kotlin.math.sqrt

class Validator(private val n: Int) {

    private fun List<Cell>.rows(): Boolean = chunked(sqrt(size.toDouble()).toInt()).all { it.validate() }

    private fun List<Cell>.columns(): Boolean {
        val list = chunked(n)
        repeat(n) { index ->
            val result = list
                .map { row -> row[index] }
                .validate()
            if (!result) return false
        }
        return true
    }

    private fun List<Cell>.regions(): Boolean {
        val regions: List<List<Cell>> = mapIndexed { index, cell -> squareIndex(index) to cell }
            .groupBy { it.first }
            .map { it.value.map { pair -> pair.second } }

        regions.forEach { println(it) }

        return regions.all { it.validate() }
    }

    private fun List<Cell>.validate() = sortedBy { it.value }.map { it.value } == (1..9).toList()

    private fun squareIndex(index: Int): Int {
        val squareColumn = (index % n) / 3
        val squareRow = (index / n) / 3
        return 3 * squareRow + squareColumn
    }

    fun validateRows(finalGrid: List<Cell>): Boolean = finalGrid.rows()

    fun validateColumns(finalGrid: List<Cell>): Boolean = finalGrid.columns()

    fun validateRegions(finalGrid: List<Cell>): Boolean = finalGrid.regions()
}
