package com.example.gymnotebook.data

import java.util.UUID

val set1 = SetOfExercise(1, 10, 100.0f, true)
val set2 = SetOfExercise(2, 11, 40.0f, false)
val setOfExercise = listOf(set1, set2)
val desc1 = ExerciseDesc(name = "Deadlift (barbell)", category = "Glutes")
val desc2 = ExerciseDesc(name = "Pull-up", category = "Back")
val desc3 = ExerciseDesc(name = "Barbell Squat", category = "Legs")

val exercise1 = Exercise(desc = desc1, sets = listOf(set1, set2, set1, set2, set1, set2))
val exercise2 = Exercise(desc = desc2, sets = listOf(set1, set2))
val exercise3 = Exercise(desc = desc3, sets = listOf())

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
    val allExercisesHM: HashMap<UUID, ExerciseDesc> = allExercisesToHM(listOf(desc1, desc2, desc3))
}

fun workoutPlansToHashMap(workoutPlans: List<WorkoutPlan>): HashMap<Int, WorkoutPlan> {
    val hm = HashMap<Int, WorkoutPlan>()
    for (plan in workoutPlans) {
        hm[plan.id] = plan
    }
    return hm
}

fun allExercisesToHM(exercises: List<ExerciseDesc>): HashMap<UUID, ExerciseDesc> {
    val hm = HashMap<UUID, ExerciseDesc>()
    for (exercise in exercises) {
        hm[exercise.descId] = exercise
    }
    return hm
}