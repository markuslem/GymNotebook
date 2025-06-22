package com.example.gymnotebook.data

import java.util.Date
import java.util.UUID

data class WorkoutPlan(
    val id: UUID = UUID.randomUUID(),
    var title: String,
    var exercisesList: List<Exercise>
)

data class Workout(
    val workoutId: UUID = UUID.randomUUID(),
    val title: String,
    val startDate: Date,
    var endDate: Date?,
    var totalWeight: Int,
    var exercises: List<Exercise>
)

// Exercise with sets that is in progress or in a workout plan
data class Exercise(
    val exerciseId: UUID = UUID.randomUUID(),
    val desc: ExerciseDesc,
    var sets: List<SetOfExercise>
)

data class SetOfExercise(
    val id: UUID = UUID.randomUUID(),
    var reps: Int,
    var weight: Float,
    var done: Boolean
)

// General information about the exercise
data class ExerciseDesc(
    val descId: UUID = UUID.randomUUID(),
    val name: String, val force: String?, val level: String,
    val mechanic: String?, val equipment: String?,
    val primaryMuscles: List<String>, val secondaryMuscles: List<String>,
    val instructions: List<String>, val category: String
) {
    fun matchesSearchQuery(query: String): Boolean {
        val queryWords = query.trim().split(" ")

        val nameWords = name.trim().split(" ")

        return queryWords.all { queryWord ->
            nameWords.any { nameWord ->
                nameWord.contains(queryWord, ignoreCase = true)
            }
        }
    }
}

