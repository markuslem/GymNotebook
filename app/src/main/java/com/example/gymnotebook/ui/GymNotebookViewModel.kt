package com.example.gymnotebook.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.gymnotebook.data.AppUiState
import com.example.gymnotebook.data.Workout
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Date

class GymNotebookViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    fun addExercise(id: Int) {
        _uiState.update { currentState ->
            currentState.copy(

            )
        }
    }

    fun startWorkout(workoutPlanId: Int) {
        Log.d("WORKOUT_STARTED", "Started a new workout with ID: $workoutPlanId")
        // Getting exercises from the workout plan
        _uiState.update { currentState ->
            val workoutPlan = currentState.workoutPlans?.get(workoutPlanId)
            val exercises = workoutPlan?.exercisesList
            val newWorkout = Workout(
                id = -1,
                workoutPlanId = workoutPlanId,
                startDate = Date(),
                endDate = null,
                totalWeight = 0,
                exercises = exercises
            )
            Log.d("WORKOUT_STARTED", "Created a new workout object: $newWorkout")
            currentState.copy(
                // Adding the created workout to the list of workouts
                currentExercises = newWorkout.exercises,
                allWorkouts = currentState.allWorkouts?.plus(newWorkout)
            )
        }
    }

    fun changeWeight(exerciseId: Int, setId: Int, newWeight: String) {
        Log.d("WORKOUT_STARTED", "Updating exercise: $exerciseId, set: $setId with $newWeight")
        _uiState.update { currentState ->
            currentState.copy(
                // Finding the exercise and set with the right IDs
                // TODO: replace with hashmap - weights

                currentExercises = currentState.currentExercises?.map { exercise ->
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

                currentExercises = currentState.currentExercises?.map { exercise ->
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

    fun finishWorkout() {
        /* Finishing the workout with the current ID
        * Saving the information about the workout to the completedWorkout object/table
        */
        _uiState.update { currentState ->
            val totalWeight = currentState.currentExercises?.sumOf { exercise ->
                exercise.sets.sumOf { it.weight.toDouble() * it.reps.toDouble() }
            }?.toFloat() ?: 0f
            currentState.copy(
//                completedWorkouts = currentState.completedWorkouts.


            )
        }
    }
}