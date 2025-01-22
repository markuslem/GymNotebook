package com.example.gymnotebook.data
val set1 = SetOfExercise(1, 10, 100.0f, true)
val set2 = SetOfExercise(2, 11, 40.0f, false)
val setOfExercise = listOf(set1, set2)
val exercise1 = Exercise(1, "Deadlift (barbell)", "Glutes", setOfExercise)
val exercise2 = Exercise(2, "Pull-up", "Back", setOfExercise)
val exercise3 = Exercise(3, "Barbell Squat", "Legs", setOfExercise)

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
        ),
        WorkoutPlan(
            title = "Legs",
            exercisesList = listOf(
                exercise1, exercise2
            )
        ),
        WorkoutPlan(
            title = "Legs",
            exercisesList = listOf(
                exercise1, exercise2
            )
        )
    )
}