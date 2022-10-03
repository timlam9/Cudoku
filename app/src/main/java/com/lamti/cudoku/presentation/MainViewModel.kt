package com.lamti.cudoku.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lamti.cudoku.domain.Cell
import com.lamti.cudoku.domain.GameEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val gameEngine: GameEngine = GameEngine(),
    private val savedState: SavedStateHandle = SavedStateHandle()
) : ViewModel() {

    val isLoading = savedState.getStateFlow(IS_LOADING, false)
    val isSolved = savedState.getStateFlow(IS_SOLVED, false)

    val board: SharedFlow<List<Cell>> = gameEngine.board

    fun onSolveClick() {
        if (isSolved.value) createNewGame() else solveBoard()
    }

    private fun createNewGame() {
        viewModelScope.launch {
            gameEngine.createNewGame()
            savedState[IS_SOLVED] = false
        }
    }

    private fun solveBoard() {
        savedState[IS_LOADING] = true
        viewModelScope.launch(Dispatchers.Default) {
            val onComplete = gameEngine.solve()
            if (onComplete) {
                savedState[IS_LOADING] = false
                savedState[IS_SOLVED] = true
            }
        }
    }

    companion object {

        private const val IS_SOLVED = "is_solved"
        private const val IS_LOADING = "is_loading"
    }
}
