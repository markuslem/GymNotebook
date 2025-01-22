package com.example.gymnotebook.data

import java.util.Date

data class CompletedWorkout(
    val id: Int,
    val startDate: Date,
    val totalWeight: Int,
    val exercises: List<Exercise>
)

data class Exercise(
    val id: Int,
    val name: String,
    val category: String,
    val sets: List<SetOfExercise>
)

data class SetOfExercise(
    val id: Int,
    var reps: Int,
    var weight: Float,
    var done: Boolean
)