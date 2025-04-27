package com.example.gymnotebook.data

import com.google.gson.Gson
import java.io.File
import java.util.Date
import java.util.UUID

fun main() {
    // Creating exercises based on: https://github.com/wrkout/exercises.json.git
    val filePath = "app/exercises.json"
    val exercisesText = File(filePath).readText()
    val gson = Gson()


    data class JsonExerciseList(val exercises: List<ExerciseDesc>)

    val exercisesList = gson.fromJson(exercisesText, JsonExerciseList::class.java)
    print(exercisesList)

}

val set1 = SetOfExercise(reps = 10, weight = 100.0f, done = false)
val set2 = SetOfExercise(reps = 11, weight = 40.0f, done = false)
val set3 = SetOfExercise(reps = 12, weight = 20.0f, done = false)
val set4 = SetOfExercise(reps = 13, weight = 10.0f, done = false)
val set5 = SetOfExercise(reps = 14, weight = 5.0f, done = false)
val set6 = SetOfExercise(reps = 15, weight = 1.0f, done = false)
val set7 = SetOfExercise(reps = 16, weight = 0.5f, done = false)

val desc1 = ExerciseDesc(
    name = "3/4 Sit-Up",
    force = "pull",
    level = "beginner",
    mechanic = "compound",
    equipment = "body only",
    primaryMuscles = listOf("abdominals"),
    secondaryMuscles = emptyList(),
    instructions = listOf(
        "Lie down on the floor and secure your feet. Your legs should be bent at the knees.",
        "Place your hands behind or to the side of your head. You will begin with your back on the ground. This will be your starting position.",
        "Flex your hips and spine to raise your torso toward your knees.",
        "At the top of the contraction your torso should be perpendicular to the ground. Reverse the motion, going only ¾ of the way down.",
        "Repeat for the recommended amount of repetitions."
    ),
    category = "strength"
)
val desc2 = ExerciseDesc(
    name = "Smith Machine Incline Bench Press",
    force = "push",
    level = "beginner",
    mechanic = "compound",
    equipment = "machine",
    primaryMuscles = listOf("chest"),
    secondaryMuscles = listOf("shoulders", "triceps"),
    instructions = listOf(
        "Place an incline bench underneath the smith machine. Place the barbell at a height that you can reach when lying down and your arms are almost fully extended. Once the weight you need is selected, lie down on the incline bench and make sure your upper chest is aligned with the barbell. Using a pronated grip (palms facing forward) that is wider than shoulder width, unlock the bar from the rack and hold it straight over you with your arms locked. This will be your starting position.",
        "As you breathe in, come down slowly until you feel the bar on your upper chest.",
        "After a second pause, bring the bar back to the starting position as you breathe out and push the bar using your chest muscles. Lock your arms in the contracted position, hold for a second and then start coming down slowly again. Tip: It should take at least twice as long to go down than to come up.",
        "Repeat the movement for the prescribed amount of repetitions.",
        "When you are done, place the bar back in the rack."
    ),
    category = "strength"
)
val desc3 = ExerciseDesc(
    name = "Muscle Up",
    force = "pull",
    level = "intermediate",
    mechanic = "compound",
    equipment = "other",
    primaryMuscles = listOf("lats"),
    secondaryMuscles = listOf(
        "abdominals",
        "biceps",
        "forearms",
        "middle back",
        "shoulders",
        "traps",
        "triceps"
    ),
    instructions = listOf(
        "Grip the rings using a false grip, with the base of your palms on top of the rings. Initiate a pull up by pulling the elbows down to your side, flexing the elbows.",
        "As you reach the top position of the pull-up, pull the rings to your armpits as you roll your shoulders forward, allowing your elbows to move straight back behind you. This puts you into the proper position to continue into the dip portion of the movement.",
        "Maintaining control and stability, extend through the elbow to complete the motion.",
        "Use care when lowering yourself to the ground."
    ),
    category = "strength"
)

val exercise1 = Exercise(desc = desc1, sets = listOf(set1, set2))
val exercise2 = Exercise(desc = desc2, sets = listOf(set1, set2))
val exercise3 = Exercise(desc = desc3, sets = listOf())

object DataSource {
    val workoutPlans: List<WorkoutPlan> = listOf(
        WorkoutPlan(
            title = "Full body",
            exercisesList = listOf(
                exercise1, exercise2, exercise3
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

fun newWorkouts(workoutPlan: WorkoutPlan): HashMap<UUID, Workout> {
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