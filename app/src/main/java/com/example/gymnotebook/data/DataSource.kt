package com.example.gymnotebook.data

val exercise1 = Exercise("Deadlift (barbell)", "Glutes", null)
val exercise2 = Exercise("Pull-up", "Back", null)
val exercise3 = Exercise("Barbell Squat", "Legs", null)

object DataSource {
    var workoutPlans: List<WorkoutPlan> = listOf(
        WorkoutPlan(
            title = "Full body",
            exercisesList = listOf(
                exercise1, exercise2, exercise3
            )
        ),
        WorkoutPlan(
            title = "Legs",
            exercisesList = listOf(
                exercise1, exercise2
            )
        ),
        WorkoutPlan(
            title = "Chess",
            exercisesList = null
        )
    )
}