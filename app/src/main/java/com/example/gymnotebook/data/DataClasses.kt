package com.example.gymnotebook.data

import java.util.Date

data class WorkoutPlan(
    val id: Int,
    var title: String,
    var exercisesList: List<Exercise>? // User does not have to specify which exercises are done
)

data class Workout(
    val id: Int,
    val workoutPlanId: Int,
    val startDate: Date,
    val endDate: Date?,
    val totalWeight: Int,
    val exercises: List<Exercise>?
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