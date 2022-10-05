package com.lamti.cudoku.domain


class Validator(private val n: Int) {

    private fun List<Cell>.rows(): Boolean = splitToRows().all { it.validate() }

    private fun List<Cell>.columns(): Boolean = splitToColumns(n).all { it.validate() }

    private fun List<Cell>.regions(): Boolean = splitToRegions(n).all { it.validate() }

    private fun List<Cell>.validate(): Boolean = sortedBy { it.value }.map { it.value } == (1..n).toList()

    fun validateRows(finalGrid: List<Cell>): Boolean = finalGrid.rows()

    fun validateColumns(finalGrid: List<Cell>): Boolean = finalGrid.columns()

    fun validateRegions(finalGrid: List<Cell>): Boolean = finalGrid.regions()

    fun validateList(list: List<Cell>): Boolean = list.validate()

    fun validateGrid(grid: List<Cell>): Boolean = grid.rows() && grid.columns() && grid.regions()
}
