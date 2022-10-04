package com.lamti.cudoku.domain

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class Solver {

    private val _board: MutableSharedFlow<List<Cell>> = MutableSharedFlow()
    val board: SharedFlow<List<Cell>> = _board.asSharedFlow()

    suspend fun updateBoard(grid: List<Cell>) {
        _board.emit(grid)
    }

    suspend fun createNewBoard(grid: List<Cell>) {
        _board.emit(grid)
    }

    suspend fun solve(initialGrid: List<Cell>): Boolean = solveBoard(initialGrid.toMutableList())

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

    private fun isPossibleInputValid(board: List<Cell>, number: Int, row: Int, column: Int, region: Int): Boolean =
        !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInRegion(board, number, region)

    private suspend fun solveBoard(board: MutableList<Cell>): Boolean {
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
                        println("Add possible input: $possibleInput in position: $index")
                        _board.emit(board)

                        if (solveBoard(board)) {
                            println("Board solved")
                            return true
                        } else {
                            println("Delete input: $possibleInput in position: $index")
                            board[index] = Cell(0, false)
                            _board.emit(board)
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

        const val GRID_SIZE = 9
    }
}
