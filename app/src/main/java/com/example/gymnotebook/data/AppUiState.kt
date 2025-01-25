package com.example.gymnotebook.data

import java.util.UUID

data class AppUiState(
    var onGoingWorkoutId: UUID? = null, // null means, that there is no ongoing workout
    // Contains all of the exercises in current workout
    val currentExercises: List<Exercise>? = listOf(),
    // Key: ID of the workout, value: workout object
    var allWorkouts: HashMap<UUID, Workout>? = HashMap(),
    var workoutPlans: HashMap<Int, WorkoutPlan>? = DataSource.workoutPlansHM
)
