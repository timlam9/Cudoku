package com.lamti.cudoku.domain

import com.lamti.cudoku.domain.Solver.Companion.GRID_SIZE

class SudokuPuzzleGenerator(private val solver: Solver = Solver()) {

    private var numbers: List<Int> = (1..GRID_SIZE).shuffled()
    private var grid: MutableList<Cell> = createEmptyGrid(GRID_SIZE).toMutableList()

    fun fillDiagonalRegions(grid: MutableList<Cell>): List<Cell> {
        var numbersIndex = 0

        grid.forEachIndexed { index, _ ->
            if (index.isInDiagonalRegion()) {
                grid[index] = Cell(numbers[numbersIndex], false)
                numbersIndex = if (numbersIndex == 8) {
                    numbers = numbers.shuffled()
                    0
                } else numbersIndex + 1
            }
        }
        return grid
    }

    fun fillBoard(): List<Cell> {
        fillGrid(0)
        return this.grid
    }

    private fun fillGrid(index: Int): Boolean {
        if (index >= grid.size) {
            printGrid()
            return true
        }

        for (n in (1..9).shuffled()) {
            if (solver.isPossibleInputValid(
                    board = grid,
                    number = n,
                    row = rowIndex(index, GRID_SIZE),
                    column = columnIndex(index, GRID_SIZE),
                    region = regionIndex(index, GRID_SIZE)
                )
            ) {
                grid[index] = Cell(n, false)
                println(" ADD NUMBER: $n at $index")

                if (fillGrid(index + 1)) {
                    println(" TRUE with: $n at $index")
                    return true
                }

                println(" REMOVE: $n at $index")
                grid[index] = Cell(0, false)
            }
        }
        return false
    }

    private fun printGrid() {
        println()
        println("============================================")
        grid.splitToRows().map { println("       " + it.map { it.value }) }
        println("============================================")
        println()
    }
}
