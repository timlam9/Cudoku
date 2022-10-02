package com.lamti.cudoku.domain

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GameEngine(private val solver: Solver = Solver()) {

    private val _board: MutableStateFlow<List<Cell>> = MutableStateFlow(createGridFrom(initialGrid))
    val board: StateFlow<List<Cell>> = _board

    fun solve() {
        val solvedGrid = solver.solve(_board.value)
        _board.update { solvedGrid }
    }
}

private const val initialGrid = "5,3,,,7,,,,\n" +
        "6,,,1,9,5,,,\n" +
        ",9,8,,,,,6,\n" +
        "8,,,,6,,,,3\n" +
        "4,,,8,,3,,,1\n" +
        "7,,,,2,,,,6\n" +
        ",6,,,,,2,8,\n" +
        ",,,4,1,9,,,5\n" +
        ",,,,8,,,7,9"

