package com.lamti.cudoku.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.withContext

class GameEngine(
    private val _board: MutableSharedFlow<List<Cell>> = MutableSharedFlow(),
    private val solver: Solver = Solver(_board),
    private val solutionChecker: SolutionChecker = SolutionChecker()
) {
    val board: SharedFlow<List<Cell>> = _board

    suspend fun solve(grid: List<Cell> = createGridFrom(initialGrid)): Boolean = withContext(Dispatchers.Default) {
        solver.solve(grid)
    }

    suspend fun createNewGame() = withContext(Dispatchers.Default) {
        _board.emit(createGridFrom(initialGrid))
    }

    suspend fun updateBoard(grid: List<Cell>) = withContext(Dispatchers.Default) {
        _board.emit(grid)
    }

    suspend fun checkForSolution(grid: List<Cell>): Boolean = withContext(Dispatchers.Default) {
        solutionChecker.checkSolution(startingGrid = createGridFrom(initialGrid), finalGrid = grid)
    }
}

