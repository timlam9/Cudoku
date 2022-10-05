package com.lamti.cudoku.domain

import org.junit.Test

class ValidatorTest {

    private val validator = Validator(9)

    @Test
    fun `Every number in the range should be present in every row`() {
        val finalGrid = createGridFrom(correctSolutionGrid)

        val result = validator.validateRows(finalGrid)

        assert(result)
    }

    @Test
    fun `Every number in the range should be present in every column`() {
        val finalGrid = createGridFrom(correctSolutionGrid)

        val result = validator.validateColumns(finalGrid)

        assert(result)
    }

    @Test
    fun `Every number in the range should be present in every region`() {
        val finalGrid = createGridFrom(correctSolutionGrid)

        val result = validator.validateRegions(finalGrid)

        assert(result)
    }

    @Test
    fun `Validate list`() {
        val list = List(9) { Cell(it + 1, false) }

        val result = validator.validateList(list)

        assert(result)
    }

    @Test
    fun `Validate grid`() {
        val finalGrid = createGridFrom(correctSolutionGrid)

        val result = validator.validateGrid(finalGrid)

        assert(result)
    }
}
