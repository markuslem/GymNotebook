package com.example.gymnotebook.data

data class AppUiState(
    var onGoingWorkoutId: Int? = -1, // -1 means, that there is no ongoing workout
    // Contains all of the exercises in current workout
    val currentExercises: List<Exercise>? = listOf(),
    var allWorkouts: List<Workout>? = listOf(),
    var workoutPlans: HashMap<Int, WorkoutPlan>? = DataSource.workoutPlansHM
)
