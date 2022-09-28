package com.lamti.cudoku.domain

import org.junit.Assert.assertFalse
import org.junit.Test

class SolverTest {

    private val solver = Solver()

     @Test
     fun `check incorrect solution`() {
         val startingGrid = createGridFrom(initialGrid)
         val finalGrid = createGridFrom(initialGrid)

         val result = solver.checkSolution(startingGrid, finalGrid)

         assertFalse(result)
     }

     @Test
     fun `check correct solution`() {
         val startingGrid = createGridFrom(initialGrid)
         val finalGrid = createGridFrom(correctSolutionGrid)

         val result = solver.checkSolution(startingGrid, finalGrid)

         assert(result)
     }
     @Test
     fun `check incorrect correct solution`() {
         val startingGrid = createGridFrom(initialGrid)
         val finalGrid = createGridFrom(anotherCorrectSolutionGrid)

         val result = solver.checkSolution(startingGrid, finalGrid)

         assertFalse(result)
     }
}
