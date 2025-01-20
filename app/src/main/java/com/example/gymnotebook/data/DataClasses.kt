package com.example.gymnotebook.data

import java.util.Date

data class CompletedWorkout(
    val startDate: Date,
    val totalWeight: Int,
    val exercises: List<Exercise>
)

data class Exercise(
    val name: String,
    val category: String,
    val sets: List<SetOfExercise>
)

data class SetOfExercise(
    var reps: Int,
    var weight: Float,
    var done: Boolean
)