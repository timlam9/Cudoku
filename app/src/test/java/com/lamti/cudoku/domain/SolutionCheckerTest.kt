package com.lamti.cudoku.domain

import org.junit.Assert.assertFalse
import org.junit.Test

class SolutionCheckerTest {

    private val solutionChecker = SolutionChecker()

     @Test
     fun `check incorrect solution`() {
         val startingGrid = createGridFrom(initialGrid)
         val finalGrid = createGridFrom(initialGrid)

         val result = solutionChecker.checkSolution(startingGrid, finalGrid)

         assertFalse(result)
     }

     @Test
     fun `check correct solution`() {
         val startingGrid = createGridFrom(initialGrid)
         val finalGrid = createGridFrom(correctSolutionGrid)

         val result = solutionChecker.checkSolution(startingGrid, finalGrid)

         assert(result)
     }
     @Test
     fun `check incorrect correct solution`() {
         val startingGrid = createGridFrom(initialGrid)
         val finalGrid = createGridFrom(anotherCorrectSolutionGrid)

         val result = solutionChecker.checkSolution(startingGrid, finalGrid)

         assertFalse(result)
     }
}
