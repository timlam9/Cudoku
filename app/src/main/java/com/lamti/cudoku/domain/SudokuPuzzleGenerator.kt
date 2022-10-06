package com.lamti.cudoku.domain

import com.lamti.cudoku.domain.Solver.Companion.GRID_SIZE

class SudokuPuzzleGenerator(private val solver: Solver = Solver()) {

    private var numbers: List<Int> = (1..GRID_SIZE).shuffled()
    private var grid: MutableList<Cell> = createEmptyGrid(GRID_SIZE).toMutableList()

    suspend fun generateGrid(level: Level = Level.JUNIOR): List<Cell> {
        fillBoard()
        return removeNumbers(level)
    }

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

    private fun fillGrid(index: Int, showLogs: Boolean = false): Boolean {
        if (index >= grid.size) {
            if (showLogs) grid.printGrid()
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
                grid[index] = Cell(n, true)
                if (showLogs) println(" ADD NUMBER: $n at $index")

                if (fillGrid(index + 1)) {
                    if (showLogs) println(" TRUE with: $n at $index")
                    return true
                }

                if (showLogs) println(" REMOVE: $n at $index")
                grid[index] = Cell(0, true)
            }
        }
        return false
    }

    suspend fun removeNumbers(level: Level = Level.JUNIOR): List<Cell> {
        var removableNumbers = GRID_SIZE * GRID_SIZE - level.initialValues

        while (removableNumbers > 0) {
            val randomIndex = grid.indices.random()

            if (grid[randomIndex].value != 0) {
                val numberToRemove = grid[randomIndex]
                grid[randomIndex] = Cell(0, false)

                if (!solver.solve(grid)) {
                    grid[randomIndex] = numberToRemove
                } else {
                    removableNumbers--
                }
            }
        }
        return grid
    }
}

fun List<Cell>.printGrid(tag: String = "") {
    if (tag != "") println()
    println(tag)
    println("============================================")
    this.splitToRows().map { println("       " + it.map { it.value }) }
    println("============================================")
    println()
}

enum class Level(val initialValues: Int) {
    JUNIOR(25),
    MID(20),
    SENIOR(17);
}
