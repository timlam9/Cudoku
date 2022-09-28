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
}
