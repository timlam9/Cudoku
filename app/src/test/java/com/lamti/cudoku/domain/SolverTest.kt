package com.lamti.cudoku.domain

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBe
import org.junit.Test

class SolverTest {

    private val solver = Solver(MutableSharedFlow())

    @Test
    fun `generate solution`() = runTest {
        val startingGrid = createGridFrom(initialGrid)

        val result = solver.solve(startingGrid)

        result shouldBe true
    }

}
