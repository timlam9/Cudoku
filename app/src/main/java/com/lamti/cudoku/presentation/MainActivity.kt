package com.lamti.cudoku.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import com.lamti.cudoku.domain.Cell
import com.lamti.cudoku.presentation.components.SudokuScreen
import com.lamti.cudoku.presentation.components.toUiBoxes
import com.lamti.cudoku.presentation.theme.CudokuTheme

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
                    val board: List<Cell> by viewModel.board.collectAsState()
                    val screenWidth = LocalConfiguration.current.screenWidthDp
                    val boxSize by remember { mutableStateOf(screenWidth / 9) }
                    val isLoading by viewModel.isLoading.collectAsState()

                    SudokuScreen(board.toUiBoxes(), boxSize, isLoading) {
                        viewModel.onSolveClick()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(viewModel: MainViewModel = MainViewModel()) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val boxSize by remember { mutableStateOf(screenWidth / 9) }

    CudokuTheme {
        SudokuScreen(viewModel.board.collectAsState().value.toUiBoxes(),boxSize, false) {}
    }
}
