package com.example.gymnotebook.data

val set1 = SetOfExercise(1, 10, 100.0f, true)
val set2 = SetOfExercise(2, 11, 40.0f, false)
val setOfExercise = listOf(set1, set2)
val exercise1 = Exercise(1, "Deadlift (barbell)", "Glutes", setOfExercise)
val exercise2 = Exercise(2, "Pull-up", "Back", setOfExercise)
val exercise3 = Exercise(3, "Barbell Squat", "Legs", setOfExercise)

object DataSource {
    val workoutPlans: List<WorkoutPlan> = listOf(
        WorkoutPlan(
            title = "Full body",
            exercisesList = listOf(
                exercise1, exercise2, exercise3, exercise1, exercise2, exercise3,
            ),
            id = 1
        ),
        WorkoutPlan(
            title = "Legs",
            exercisesList = listOf(
                exercise1, exercise2
            ),
            id = 3
        ),
        WorkoutPlan(
            title = "Chess",
            exercisesList = null,
            id = 4
        ),
        WorkoutPlan(
            title = "Legs",
            exercisesList = listOf(
                exercise1, exercise2
            ),
            id = 5
        ),
        WorkoutPlan(
            title = "Legs",
            exercisesList = listOf(
                exercise1, exercise2
            ),
            id = 6
        )
    )
    val workoutPlansHM: HashMap<Int, WorkoutPlan> = workoutPlansToHashMap(workoutPlans)
}

fun workoutPlansToHashMap(workoutPlans: List<WorkoutPlan>): HashMap<Int, WorkoutPlan> {
    val hm = HashMap<Int, WorkoutPlan>()
    for (plan in workoutPlans) {
        hm[plan.id] = plan
    }
    return hm
}