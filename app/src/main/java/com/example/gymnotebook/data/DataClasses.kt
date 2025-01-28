package com.example.gymnotebook.data

import java.util.Date
import java.util.UUID

data class WorkoutPlan(
    val id: Int,
    var title: String,
    var exercisesList: List<Exercise>? // User does not have to specify which exercises are done
)

data class Workout(
    val workoutId: UUID = UUID.randomUUID(),
    val workoutPlanId: Int,
    val startDate: Date,
    var endDate: Date?,
    var totalWeight: Int,
    val exercises: List<Exercise>?
)

// Exercise with sets that is in progress or in a workout plan
data class Exercise(
    val exerciseId: UUID = UUID.randomUUID(),
    val desc: ExerciseDesc,
    val sets: List<SetOfExercise>
)

data class SetOfExercise(
    val id: Int,
    var reps: Int,
    var weight: Float,
    var done: Boolean
)

// General information about the exercise
data class ExerciseDesc(
    val descId: UUID = UUID.randomUUID(),
    val name: String,
    val category: String,
)

