package com.example.gymnotebook.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gymnotebook.ui.GymNotebookViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

enum class AppScreen(barTitle: String) {
    RecordWorkout(barTitle = "Record a new workout"),
    OngoingWorkout(barTitle = "Ongoing workout"),
    Exercises(barTitle = "Exercises"),
    WorkoutHistory(barTitle = "Workout History"),
    WorkoutPlans(barTitle = "Workout Plans"),
    Profile(barTitle = "Profile")
}

@Composable
fun GymNotebookApp(
    viewModel: GymNotebookViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.RecordWorkout.name
    )

    Scaffold(
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        //RecordWorkoutScreen(modifier = Modifier.padding(innerPadding))

        NavHost(
            navController = navController,
            startDestination = AppScreen.RecordWorkout.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            /* Screen where user can select which workout to choose */
            composable(route = AppScreen.RecordWorkout.name) {
                RecordWorkoutScreen(
                    onQuickStartBtnClicked = {
                        println("test")
                        navController.navigate(AppScreen.OngoingWorkout.name)
                    },
                    startWorkout = { workoutId ->
                        navController.navigate(AppScreen.OngoingWorkout.name)
                        viewModel.startWorkout(workoutId)
                    }
                )
            }

            /* Screen of an ongoing workout */
            composable(route = AppScreen.OngoingWorkout.name) {
                OngoingWorkoutScreen(
                    onWeightChanged = { exerciseId, setId, newWeight ->
                        viewModel.changeWeight(exerciseId, setId, newWeight)
                    },
                    onRepsChanged = { exerciseId, setId, newReps ->
                        viewModel.changeReps(exerciseId, setId, newReps)
                    },
                    uiState = uiState,
                    onWorkoutFinished = {
                        navController.navigate(AppScreen.RecordWorkout.name)
                        viewModel.finishWorkout()
                    },
                    addExercise = {
                        navController.navigate(AppScreen.Exercises.name)
                    },
                    addSet = { id ->
                        viewModel.addSetToOngoing(id)
                    }
                )
            }

            /* Screen displaying all exercises */
            composable(route = AppScreen.Exercises.name) {
                ExerciseSelectionScreen(
                    allExercises = uiState.allExercises,
                    addSelectedExercise = { exercise ->
                        navController.navigate(AppScreen.OngoingWorkout.name)
                        // Adding the exercise which is currently selected in ExerciseSelectionScreen
                        viewModel.addExerciseToOngoing(exercise)
                    },
                    cancelExerciseSelection = { navController.navigate(AppScreen.OngoingWorkout.name) }
                )
            }

        }
    }
}