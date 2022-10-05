package com.lamti.cudoku.domain

class SudokuPuzzleGenerator {

    private var numbers: List<Int> = (1..Solver.GRID_SIZE).shuffled()

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
}
