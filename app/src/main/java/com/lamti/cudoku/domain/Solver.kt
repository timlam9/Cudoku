package com.lamti.cudoku.domain

class Solver {

    fun solve(startingGrid: List<Cell>): List<Cell> {
        val solverGrid: MutableList<Cell> = startingGrid.toMutableList()

        println("================== Initial grid ==================")
        solverGrid.splitToRows().forEach { println("          " + it.map { it.value }) }
        println("==================================================")

        solveBoard(solverGrid)

        println("================== Final grid ==================")
        solverGrid.splitToRows().forEach { println("          " + it.map { it.value }) }
        println("================================================")


        return solverGrid
    }


    private fun isNumberInRow(board: List<Cell>, number: Int, row: Int): Boolean {
        for (i in 0 until GRID_SIZE) {
            if (board.splitToRows()[row][i].value == number) {
//                println("Number $number found in row: $row")
                return true
            }
        }
//        println("Number $number not found in the row")
        return false
    }

    private fun isNumberInColumn(board: List<Cell>, number: Int, column: Int): Boolean {
        for (i in 0 until GRID_SIZE) {
            if (board.splitToColumns(GRID_SIZE)[column][i].value == number) {
//                println("Number $number found in column: $column")
                return true
            }
        }
//        println("Number $number not found in the column")
        return false
    }

    private fun isNumberInRegion(board: List<Cell>, number: Int, region: Int): Boolean {
        for (i in 0 until GRID_SIZE) {
            if (board.splitToRegions(GRID_SIZE)[region][i].value == number) {
//                println("Number $number found in region: $region")
                return true
            }
        }
//        println("Number $number not found in the region")
        return false
    }

    private fun isPossibleInputValid(board: List<Cell>, number: Int, row: Int, column: Int, region: Int): Boolean =
        !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInRegion(board, number, region)

    private fun solveBoard(board: MutableList<Cell>): Boolean {
        for (index in 0 until board.size) {
            if (board[index].value == 0) {
                for (possibleInput in 1..GRID_SIZE) {
                    if (isPossibleInputValid(board = board, number = possibleInput, row = rowIndex(index, GRID_SIZE), column = columnIndex(index, GRID_SIZE), region = regionIndex(index, GRID_SIZE))) {
                        board[index] = Cell(possibleInput)
                        println("Add possible input: $possibleInput in position: $index")

                        if (solveBoard(board)) {
                            println("Board solved")
                            return true
                        } else {
                            println("Delete input: $possibleInput in position: $index")
                            board[index] = Cell(0)
                        }
                    }
                }
                println("Board didn't solved")
                return false
            }
        }
        println("Board is solved")
        return true
    }

    companion object {

        private const val GRID_SIZE = 9
    }
}
