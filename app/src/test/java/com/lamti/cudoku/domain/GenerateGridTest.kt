package com.lamti.cudoku.domain

import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalStdlibApi::class)
class GenerateGridTest {

    @Test
    fun `generate empty grid`() {
        val expectedGrid: List<Cell> = buildList {
            repeat(9 * 9) {
                add(Cell(0))
            }
        }

        val actualGrid = createEmptyGrid(9)

        assertEquals(expectedGrid, actualGrid)
    }

    @Test
    fun `generate empty grid from empty file`() {
        val expectedGrid: List<Cell> = buildList {
            repeat(9 * 9) {
                add(Cell(0))
            }
        }

        val actualGrid = createGridFrom(emptyInitialGrid)

        assertEquals(expectedGrid, actualGrid)
    }

    @Test
    fun `generate valid initial grid`() {
        val expectedGrid: List<Cell> = listOf(
            Cell(5), Cell(3), Cell(0), Cell(0), Cell(7), Cell(0), Cell(0), Cell(0), Cell(0),
            Cell(6), Cell(0), Cell(0), Cell(1), Cell(9), Cell(5), Cell(0), Cell(0), Cell(0),
            Cell(0), Cell(9), Cell(8), Cell(0), Cell(0), Cell(0), Cell(0), Cell(6), Cell(0),
            Cell(8), Cell(0), Cell(0), Cell(0), Cell(6), Cell(0), Cell(0), Cell(0), Cell(3),
            Cell(4), Cell(0), Cell(0), Cell(8), Cell(0), Cell(3), Cell(0), Cell(0), Cell(1),
            Cell(7), Cell(0), Cell(0), Cell(0), Cell(2), Cell(0), Cell(0), Cell(0), Cell(6),
            Cell(0), Cell(6), Cell(0), Cell(0), Cell(0), Cell(0), Cell(2), Cell(8), Cell(0),
            Cell(0), Cell(0), Cell(0), Cell(4), Cell(1), Cell(9), Cell(0), Cell(0), Cell(5),
            Cell(0), Cell(0), Cell(0), Cell(0), Cell(8), Cell(0), Cell(0), Cell(7), Cell(9),
        )

        val actualGrid = createGridFrom(initialGrid)

        assertEquals(expectedGrid, actualGrid)
    }
}


