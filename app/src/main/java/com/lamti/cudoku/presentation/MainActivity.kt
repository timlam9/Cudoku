package com.lamti.cudoku.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.lifecycleScope
import com.lamti.cudoku.domain.createGridFrom
import com.lamti.cudoku.domain.initialGrid
import com.lamti.cudoku.presentation.components.SudokuScreen
import com.lamti.cudoku.presentation.components.toUiBoxes
import com.lamti.cudoku.presentation.theme.CudokuTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CudokuTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val screenWidth = LocalConfiguration.current.screenWidthDp
                    val boxSize by remember { mutableStateOf(screenWidth / 9) }

                    val isLoading by viewModel.isLoading.collectAsState()
                    val isSolved by viewModel.isSolved.collectAsState()

                    val board = remember { mutableStateOf(createGridFrom(initialGrid).toUiBoxes()) }

                    LaunchedEffect(Unit) {
                        viewModel.board.onEach { board.value = it.toUiBoxes() }.launchIn(lifecycleScope)
                    }

                    SudokuScreen(board.value, boxSize, isLoading, isSolved) {
                        viewModel.onSolveClick()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(viewModel: MainViewModel = MainViewModel(savedState = SavedStateHandle())) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val boxSize by remember { mutableStateOf(screenWidth / 9) }

    CudokuTheme {
        SudokuScreen(
            viewModel.board.collectAsState(createGridFrom(initialGrid)).value.toUiBoxes(),
            boxSize,
            isLoading = false,
            isSolved = false
        ) {}
    }
}
