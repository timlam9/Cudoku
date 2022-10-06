package com.lamti.cudoku.domain

import kotlinx.coroutines.flow.MutableSharedFlow

class Solver(private val currentBoard: MutableSharedFlow<List<Cell>> = MutableSharedFlow()) {

    suspend fun solve(initialGrid: List<Cell>, shouldEmitValues: Boolean = false): Boolean = solveBoard(initialGrid.toMutableList(), shouldEmitValues)

    private fun isNumberInRow(board: List<Cell>, number: Int, row: Int): Boolean {
        for (i in 0 until GRID_SIZE) {
            if (board.splitToRows()[row][i].value == number) {
                return true
            }
        }
        return false
    }

    private fun isNumberInColumn(board: List<Cell>, number: Int, column: Int): Boolean {
        for (i in 0 until GRID_SIZE) {
            if (board.splitToColumns(GRID_SIZE)[column][i].value == number) {
                return true
            }
        }
        return false
    }

    private fun isNumberInRegion(board: List<Cell>, number: Int, region: Int): Boolean {
        for (i in 0 until GRID_SIZE) {
            if (board.splitToRegions(GRID_SIZE)[region][i].value == number) {
                return true
            }
        }
        return false
    }

    fun isPossibleInputValid(board: List<Cell>, number: Int, row: Int, column: Int, region: Int): Boolean =
        !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInRegion(board, number, region)

    private suspend fun solveBoard(board: MutableList<Cell>, shouldEmitValues: Boolean): Boolean {
        for (index in 0 until board.size) {
            if (board[index].value == 0) {
                for (possibleInput in 1..GRID_SIZE) {
                    if (isPossibleInputValid(
                            board = board,
                            number = possibleInput,
                            row = rowIndex(index, GRID_SIZE),
                            column = columnIndex(index, GRID_SIZE),
                            region = regionIndex(index, GRID_SIZE)
                        )
                    ) {
                        board[index] = Cell(possibleInput, false)

                        if (shouldEmitValues) currentBoard.emit(board)

                        if (solveBoard(board, shouldEmitValues)) {
                            return true
                        } else {
                            board[index] = Cell(0, false)
                            if (shouldEmitValues) currentBoard.emit(board)
                        }
                    }
                }
                return false
            }
        }
        return true
    }

    companion object {

        const val GRID_SIZE = 9
    }
}
