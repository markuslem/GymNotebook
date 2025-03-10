package com.example.gymnotebook.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.gymnotebook.data.AppUiState
import com.example.gymnotebook.data.Exercise
import com.example.gymnotebook.data.ExerciseDesc
import com.example.gymnotebook.data.SetOfExercise
import com.example.gymnotebook.data.Workout
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Date
import java.util.UUID

class GymNotebookViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()


    fun startWorkout(workoutPlanId: UUID) {
        Log.d("WORKOUT", "Started a new workout with workout plan ID: $workoutPlanId")
        // Getting exercises from the workout plan
        _uiState.update { currentState ->
            val workoutPlan = currentState.workoutPlans.get(workoutPlanId)
            val exercises = workoutPlan?.exercisesList
            val newWorkout = Workout(
                title = workoutPlan?.title ?: "Unnamed workout",
                startDate = Date(),
                endDate = null,
                totalWeight = 0,
                exercises = exercises ?: listOf()
            )
            currentState.allWorkouts.put(newWorkout.workoutId, newWorkout)
            Log.d("WORKOUT", "New workout object is added to HM: $newWorkout")
            currentState.copy(
                // Keeps track of the active workout ID to access it later conveniently
                currentExercises = newWorkout.exercises,
                onGoingWorkoutId = newWorkout.workoutId
            )
        }
    }

    fun quickStartWorkout() {
        Log.d("WORKOUT", "Quick started a new workout")
        val newWorkout = Workout(
            title = "Quick start",
            startDate = Date(),
            endDate = null,
            totalWeight = 0,
            exercises = listOf()
        )

        // Creating a new workout
        _uiState.update { currentState ->
            currentState.allWorkouts.put(newWorkout.workoutId, newWorkout)
            Log.d("WORKOUT", "New workout object is added to HM: $newWorkout")
            currentState.copy(
                // Keeps track of the active workout ID to access it later conveniently
                currentExercises = newWorkout.exercises,
                onGoingWorkoutId = newWorkout.workoutId
            )

        }
    }

    fun changeWeight(exerciseId: UUID, setId: UUID, newWeight: Float) {
        Log.d("WORKOUT", "Updating exercise: $exerciseId, set: $setId with $newWeight")
        _uiState.update { currentState ->
            currentState.copy(
                // Finding the exercise and set with the right IDs

                currentExercises = currentState.currentExercises?.map { exercise ->
                    if (exercise.exerciseId == exerciseId) {
                        exercise.copy(
                            sets = exercise.sets.map { set ->
                                if (set.id == setId) set.copy(weight = newWeight) else set
                            }
                        )
                    } else {
                        exercise
                    }
                }
            )
        }
    }

    /**
     * Changing the repetition count from one positive integer to another
     * @param exerciseId The UUID of the exercise object
     * @param setId UUID of the set where the value should change
     * @param newReps Input in the form of a String from the user
     */
    fun changeReps(exerciseId: UUID, setId: UUID, newReps: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                // Finding the exercise and set with the right IDs

                currentExercises = currentState.currentExercises?.map { exercise ->
                    if (exercise.exerciseId == exerciseId) {
                        exercise.copy(
                            sets = exercise.sets.map { set ->
                                if (set.id == setId) set.copy(reps = newReps) else set
                            }
                        )
                    } else {
                        exercise
                    }
                }
            )

        }
    }

    fun finishWorkout() {
        /* Finishing the workout with the currently active UUID.
        * Writing end date and calculating total weight lifted.
        * Removing workout info from currentState currentExercises and onGoingWorkoutId fields.
        */
        _uiState.update { currentState ->

            // Calculation of total weight lifted
            val totalWeight = currentState.currentExercises?.sumOf { exercise ->
                exercise.sets.sumOf { it.weight.toDouble() * it.reps.toDouble() }
            }?.toInt() ?: 0

            // Writing totalWeight and end date to the workout object
            val currentWorkout = currentState.allWorkouts[currentState.onGoingWorkoutId]
            Log.i(
                "WORKOUT",
                "Added endDate, totalWeight and done exercises to workout: {onGoingWorkoutId: ${currentWorkout?.workoutId}, title: ${currentWorkout?.title}, exercises: ${currentWorkout?.exercises}}"
            )
            if (currentWorkout == null) {
                Log.e(
                    "WORKOUT",
                    "Didn't find a workout UUID: ${currentState.onGoingWorkoutId}"
                )
            } else {
                currentWorkout.endDate = Date()
                currentWorkout.totalWeight = totalWeight
                // Filtering out the exercises that are not done
                Log.d("WORKOUT", "The sets were: ")
                currentState.currentExercises?.forEach( {exercise ->
                    exercise.sets.forEach({Log.d("WORKOUT", "Set: $it")})
                })
                currentState.currentExercises?.forEach({
                        exercise -> exercise.sets = exercise.sets.filter { it.done }
                })
                if (currentState.currentExercises != null)
                    currentWorkout.exercises = currentState.currentExercises ?: listOf()
                else
                    currentWorkout.exercises = listOf()


            }

            Log.d("WORKOUT", "Successfully finished workout UUID: ${currentWorkout?.workoutId}")
            currentState.copy(
//                completedWorkouts = currentState.completedWorkouts.
                currentExercises = listOf(),
                onGoingWorkoutId = null // Shows that there is no workout going on
            )
        }
    }

    // Adding exercise to list of ongoing exercises
    fun addExerciseToOngoing(exerciseDesc: ExerciseDesc?) {
        if (exerciseDesc == null) {
            Log.d("WORKOUT", "There was no selected exercise")
            return
        }
        val addedExercise = Exercise(desc = exerciseDesc, sets = listOf())

        _uiState.update { currentState ->
            currentState.currentExercises?.plus(addedExercise)
            currentState.copy()
        }
        Log.d("WORKOUT", "Added exercise: $addedExercise to current workout")
    }

    fun addSetToOngoing(exerciseId: UUID) {
        val newSet = SetOfExercise(
            reps = 0,
            weight = 0f,
            done = false
        )
        _uiState.update { currentState ->
            val exercise = currentState.currentExercises?.find { it.exerciseId == exerciseId }
            if (exercise != null) {
                exercise.sets = exercise.sets.plus(newSet)
            } else {
                Log.d("WORKOUT", "Failed to add a new set to exercise: $exerciseId")
            }

            currentState.copy()
        }
        Log.d("WORKOUT", "Added new set to exercise: $exerciseId")
    }

    /* Changing the value of done field in a set */
    fun doneChanged(exerciseId: UUID, setId: UUID, checked: Boolean) {
        _uiState.update { currentState ->
            val set = currentState.currentExercises?.find { it.exerciseId == exerciseId }?.sets?.find { it.id == setId }
            set?.done = checked
            currentState.copy()
        }
        Log.d("WORKOUT", "changed value to: $checked")

    }
}