package com.lamti.cudoku.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lamti.cudoku.domain.Cell
import com.lamti.cudoku.domain.GameEngine
import com.lamti.cudoku.domain.Level
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(
    private val gameEngine: GameEngine = GameEngine(),
    private val savedState: SavedStateHandle = SavedStateHandle()
) : ViewModel() {

    val isBoxClicked = savedState.getStateFlow(IS_BOX_CLICKED, false)
    val isLoading = savedState.getStateFlow(IS_LOADING, false)
    val isSolved = savedState.getStateFlow(IS_SOLVED, false)
    val boxIndexClicked = savedState.getStateFlow(BOX_INDEX_CLICKED, -1)

    val board: SharedFlow<List<Cell>> = gameEngine.board

    init {
        board
            .filterNot { isLoading.value }
            .filterNot { it.map { it.value }.contains(0) }
            .onEach { grid -> savedState[IS_SOLVED] = gameEngine.checkForSolution(grid) }
            .launchIn(viewModelScope)

        isSolved
            .filter { it }
            .onEach {
                savedState[IS_LOADING] = false
                savedState[IS_BOX_CLICKED] = false
            }
            .distinctUntilChanged()
            .launchIn(viewModelScope)
    }

    fun onBoxClick(index: Int) {
        savedState[IS_BOX_CLICKED] = index != boxIndexClicked.value && index != -1
        savedState[BOX_INDEX_CLICKED] = index
    }

    fun onKeyboardNumberClick(number: Int, grid: MutableList<Cell>) {
        viewModelScope.launch {
            grid[boxIndexClicked.value] = Cell(number, false)
            gameEngine.updateBoard(grid)
        }
    }

    fun onSolveClick(board: List<Cell>) {
        if (isSolved.value) createNewGame() else solveBoard(board)
    }

    private fun createNewGame() {
        viewModelScope.launch {
            savedState[IS_LOADING] = true
            savedState[IS_SOLVED] = false
            gameEngine.createNewGame(Level.JUNIOR) {
                savedState[IS_LOADING] = false
            }
        }
    }

    private fun solveBoard(board: List<Cell>) {
        savedState[IS_LOADING] = true
        viewModelScope.launch(Dispatchers.Default) {
            if (gameEngine.solve(board)) {
                savedState[IS_LOADING] = false
                savedState[IS_SOLVED] = true
            }
        }
    }

    companion object {

        private const val IS_SOLVED = "is_solved"
        private const val IS_LOADING = "is_loading"
        private const val IS_BOX_CLICKED = "is_box_clicked"
        private const val BOX_INDEX_CLICKED = "box_index_clicked"
    }
}
