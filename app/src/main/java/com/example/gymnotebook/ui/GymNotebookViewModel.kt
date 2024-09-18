package com.example.gymnotebook.ui

import androidx.lifecycle.ViewModel
import com.example.gymnotebook.data.AppUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GymNotebookViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    fun addExercise(id: Int) {
        _uiState.update { currentState ->
            currentState.copy(

            )
        }
    }
}