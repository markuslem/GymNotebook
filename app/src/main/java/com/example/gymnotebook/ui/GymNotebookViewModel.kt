package com.example.gymnotebook.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.gymnotebook.data.AppUiState
import com.example.gymnotebook.data.DataSource
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

    fun changeWeight(exerciseId: Int, setId: Int, newWeight: String) {
        Log.d("CHANGE_WEIGHT", "Updating exercise: $exerciseId, set: $setId with $newWeight")
        _uiState.update { currentState ->
            currentState.copy(
                // Finding the exercise and set with the right IDs
                // TODO: replace with hashmap - weights

                allExercises = currentState.allExercises?.map { exercise ->
                    if (exercise.id == exerciseId) {
                        exercise.copy(
                            sets = exercise.sets.map { set ->
                                if (set.id == setId) set.copy(weight = newWeight.toFloat()) else set
                            }
                        )
                    } else {
                        exercise
                    }
                }
            )
        }
    }

    fun changeReps(exerciseId: Int, setId: Int, newReps: String) {
        _uiState.update { currentState ->
            currentState.copy(
                // Finding the exercise and set with the right IDs
                // TODO: replace with hashmap - reps

                allExercises = currentState.allExercises?.map { exercise ->
                    if (exercise.id == exerciseId) {
                        exercise.copy(
                            sets = exercise.sets.map { set ->
                                if (set.id == setId) set.copy(reps = newReps.toInt()) else set
                            }
                        )
                    } else {
                        exercise
                    }
                }
            )

        }
    }
}