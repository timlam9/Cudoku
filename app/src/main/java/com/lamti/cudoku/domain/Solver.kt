package com.lamti.cudoku.domain

class Solver(private val validator: Validator = Validator(9)) {

    fun checkSolution(startingGrid: List<Cell>, finalGrid: List<Cell>): Boolean {
        return finalGrid.isSolved() && finalGrid.matches(startingGrid)
    }


    private fun List<Cell>.isSolved(): Boolean = with(validator) {
        validateRows(this@isSolved) && validateColumns(this@isSolved) && validateRegions(this@isSolved)
    }

    private fun List<Cell>.matches(startingGrid: List<Cell>): Boolean {
        val finalMap: List<IndexedValue<Cell>> = this.withIndex().toList()
        return startingGrid
            .withIndex()
            .toList()
            .filter { it.value.value != 0 }
            .all { finalMap[it.index].value.value == it.value.value }
    }
}
