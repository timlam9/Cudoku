package com.lamti.cudoku.domain

import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalStdlibApi::class)
class GenerateGridTest {

    @Test
    fun `generate empty grid`() {
        val expectedGrid: List<Cell> = buildList {
            repeat(9 * 9) {
                add(Cell(0, false))
            }
        }

        val actualGrid = createEmptyGrid(9)

        assertEquals(expectedGrid, actualGrid)
    }

    @Test
    fun `generate empty grid from empty file`() {
        val expectedGrid: List<Cell> = buildList {
            repeat(9 * 9) {
                add(Cell(0,false))
            }
        }

        val actualGrid = createGridFrom(emptyInitialGrid)

        assertEquals(expectedGrid, actualGrid)
    }

    @Test
    fun `generate valid initial grid`() {
        val expectedGrid: List<Cell> = listOf(
            Cell(5,true), Cell(3,true), Cell(0,false), Cell(0,false), Cell(7,true), Cell(0,false), Cell(0,false), Cell(0,false), Cell(0,false),
            Cell(6,true), Cell(0,false), Cell(0,false), Cell(1,true), Cell(9,true), Cell(5,true), Cell(0,false), Cell(0,false), Cell(0,false),
            Cell(0,false), Cell(9,true), Cell(8,true), Cell(0,false), Cell(0,false), Cell(0,false), Cell(0,false), Cell(6,true), Cell(0,false),
            Cell(8,true), Cell(0,false), Cell(0,false), Cell(0,false), Cell(6,true), Cell(0,false), Cell(0,false), Cell(0,false), Cell(3,true),
            Cell(4,true), Cell(0,false), Cell(0,false), Cell(8,true), Cell(0,false), Cell(3,true), Cell(0,false), Cell(0,false), Cell(1,true),
            Cell(7,true), Cell(0,false), Cell(0,false), Cell(0,false), Cell(2,true), Cell(0,false), Cell(0,false), Cell(0,false), Cell(6,true),
            Cell(0,false), Cell(6,true), Cell(0,false), Cell(0,false), Cell(0,false), Cell(0,false), Cell(2,true), Cell(8,true), Cell(0,false),
            Cell(0,false), Cell(0,false), Cell(0,false), Cell(4,true), Cell(1,true), Cell(9,true), Cell(0,false), Cell(0,false), Cell(5,true),
            Cell(0,false), Cell(0,false), Cell(0,false), Cell(0,false), Cell(8,true), Cell(0,false), Cell(0,false), Cell(7,true), Cell(9,true),
        )

        val actualGrid = createGridFrom(initialGrid)

        assertEquals(expectedGrid, actualGrid)
    }
}


