package com.lamti.cudoku.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lamti.cudoku.domain.Cell
import com.lamti.cudoku.domain.GameEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(private val gameEngine: GameEngine = GameEngine()) : ViewModel() {

    private var _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    val board: StateFlow<List<Cell>> = gameEngine.board

    init {
        board.onEach { _isLoading.update { false } }.launchIn(viewModelScope)
    }

    fun onSolveClick() {
        _isLoading.update { true }
        viewModelScope.launch(Dispatchers.Default) {
            gameEngine.solve()
        }
    }
}
