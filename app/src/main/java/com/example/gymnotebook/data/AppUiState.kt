package com.example.gymnotebook.data



data class AppUiState(
    val weight: Float = 1.0f,
    val reps: Int = 1,
    val allExercises: List<Exercise>? = DataSource.workoutPlans[0].exercisesList
)
