package com.lamti.cudoku.domain

import com.lamti.cudoku.domain.Solver.Companion.GRID_SIZE
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeTrue
import org.junit.Test

class SudokuPuzzleGeneratorTest {

    private val validator = Validator(GRID_SIZE)
    private val solver = Solver()
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

    @Test
    fun `fill board with valid numbers`() {
        validator.validateGrid(puzzleGenerator.fillBoard()).shouldBeTrue()
    }

    @Test
    fun `remove numbers`() = runTest {
        puzzleGenerator.fillBoard()

        val puzzle: List<Cell> = puzzleGenerator.removeNumbers()

        puzzle.filterNot { it.value == 0 }.size shouldBeEqualTo Level.JUNIOR.initialValues
        solver.solve(puzzle) shouldBe true
    }
}
