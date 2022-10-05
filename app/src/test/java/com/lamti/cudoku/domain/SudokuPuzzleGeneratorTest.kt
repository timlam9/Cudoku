package com.lamti.cudoku.domain

import com.lamti.cudoku.domain.Solver.Companion.GRID_SIZE
import org.amshove.kluent.shouldBeTrue
import org.junit.Test

class SudokuPuzzleGeneratorTest {

    private val validator = Validator(GRID_SIZE)
    private val puzzleGenerator = SudokuPuzzleGenerator()

    @Test
    fun `fill diagonal regions with valid numbers`() {
        val diagonallyFilledGrid = puzzleGenerator.fillDiagonalRegions(createEmptyGrid(GRID_SIZE).toMutableList())

        diagonallyFilledGrid
            .splitToRegions(GRID_SIZE)
            .filterIndexed { index, _ -> index == 0 || index == 4 || index == 8 }
            .all { validator.validateList(it) }
            .shouldBeTrue()
    }
}
