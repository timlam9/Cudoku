package com.lamti.cudoku.domain

import kotlinx.coroutines.flow.SharedFlow

class GameEngine(private val solver: Solver = Solver()) {

    val board: SharedFlow<List<Cell>> = solver.board

    suspend fun solve(grid: List<Cell> = createGridFrom(initialGrid)): Boolean = solver.solve(grid)

    suspend fun createNewGame() {
        solver.createNewBoard(createGridFrom(initialGrid))
    }
}

const val initialGrid = "5,3,,,7,,,,\n" +
        "6,,,1,9,5,,,\n" +
        ",9,8,,,,,6,\n" +
        "8,,,,6,,,,3\n" +
        "4,,,8,,3,,,1\n" +
        "7,,,,2,,,,6\n" +
        ",6,,,,,2,8,\n" +
        ",,,4,1,9,,,5\n" +
        ",,,,8,,,7,9"

