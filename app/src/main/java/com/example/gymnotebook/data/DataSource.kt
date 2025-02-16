package com.example.gymnotebook.data

import java.util.Date
import java.util.UUID

val set1 = SetOfExercise(reps = 10, weight = 100.0f, done = true)
val set2 = SetOfExercise(reps = 11, weight = 40.0f, done = false)
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
        ),
        WorkoutPlan(
            title = "Legs",
            exercisesList = listOf(
                exercise1, exercise2
            ),
        ),
        WorkoutPlan(
            title = "Chess",
            exercisesList = listOf(),
        ),
        WorkoutPlan(
            title = "Legs",
            exercisesList = listOf(
                exercise1, exercise2
            ),
        ),
        WorkoutPlan(
            title = "Legs",
            exercisesList = listOf(
                exercise1, exercise2
            ),
        )
    )
    val workoutPlansHM: HashMap<UUID, WorkoutPlan> = workoutPlansToHashMap(workoutPlans)
    val allExercisesHM: HashMap<UUID, ExerciseDesc> = allExercisesToHM(listOf(desc1, desc2, desc3))
    val exampleWorkouts: HashMap<UUID, Workout> = newWorkouts(workoutPlans[0])
}

fun workoutPlansToHashMap(workoutPlans: List<WorkoutPlan>): HashMap<UUID, WorkoutPlan> {
    val hm = HashMap<UUID, WorkoutPlan>()
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

fun newWorkouts(workoutPlan : WorkoutPlan): HashMap<UUID, Workout> {
    val hm = HashMap<UUID, Workout>()
    val workout1 = Workout(
        title = workoutPlan.title,
        startDate = Date(),
        endDate = Date(),
        totalWeight = 100,
        exercises = workoutPlan.exercisesList
    )
    val workout2 = Workout(
        title = workoutPlan.title,
        startDate = Date(),
        endDate = Date(),
        totalWeight = 100,
        exercises = workoutPlan.exercisesList
    )
    hm[workout1.workoutId] = workout1
    hm[workout2.workoutId] = workout2
    return hm
}