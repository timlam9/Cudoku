package com.lamti.cudoku.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class SolverTest {

    private val solver = Solver()

    @Test
    fun `generate solution`() {
        val startingGrid = createGridFrom(initialGrid)
        val finalGrid = createGridFrom(correctSolutionGrid).map { it.value }

        val result = solver.solve(startingGrid).map { it.value }

        assertEquals(finalGrid, result)
    }

}
