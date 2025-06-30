package com.example.gymnotebook.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gymnotebook.data.ExerciseDesc
import com.example.gymnotebook.ui.GymNotebookViewModel
import java.util.UUID

enum class AppScreen(barTitle: String) {
    RecordWorkout(barTitle = "Record a new workout"),
    OngoingWorkout(barTitle = "Ongoing workout"),
    ExercisesSelection(barTitle = "Select an exercis"),
    AllExercises(barTitle = "All exercises"),
    WorkoutHistory(barTitle = "Workout History"),
    WorkoutPlans(barTitle = "Workout Plans"),
    Profile(barTitle = "Profile")
}

@Composable
fun GymNotebookApp(
    viewModel: GymNotebookViewModel = viewModel<GymNotebookViewModel>(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.RecordWorkout.name
    )
    Scaffold(
        /* Bottom navigation bar */
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                // List of screens which have a corresponding button in the bottom navigation bar
                val listOfNavScreens = listOf(
                    AppScreen.RecordWorkout,
                    AppScreen.WorkoutHistory,
                    AppScreen.AllExercises
                )
                listOfNavScreens.forEach { screen ->
                    BottomNavigationItem(
                        selected = currentDestination?.hierarchy?.any { it.route == screen.name } == true,
                        onClick = {
                            navController.navigate(screen.name)
                        },
                        icon = {
                            Text(screen.name)
                        })
                }
            }
        }


    )
    { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        val searchText by viewModel.searchText.collectAsState()
        val displayedExercises by viewModel.displayedExercises.collectAsState()

        NavHost(
            navController = navController,
            startDestination = AppScreen.RecordWorkout.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            /* Screen where user can select which workout to choose */
            composable(route = AppScreen.RecordWorkout.name) {
                RecordWorkoutScreen(

                    startWorkout = { workoutId ->
                        navController.navigate(AppScreen.OngoingWorkout.name)
                        viewModel.startWorkout(workoutId)
                    },
                    quickStartWorkout = {
                        viewModel.quickStartWorkout()
                        navController.navigate(AppScreen.OngoingWorkout.name)
                    },
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
                    onDoneChanged = { exerciseId, setId, checked ->
                        viewModel.doneChanged(exerciseId, setId, checked)
                    },
                    exercises = uiState.currentExercises ?: listOf(),
                    onWorkoutFinished = {
                        navController.navigate(AppScreen.RecordWorkout.name)
                        viewModel.finishWorkout()
                    },
                    chooseExercise = {
                        navController.navigate(AppScreen.ExercisesSelection.name)
                    },
                    addSet = { id ->
                        viewModel.addSetToOngoing(id)
                    }
                )
            }

            /* Screen displaying all exercises.
            * The user can select one exercise
            */
            composable(route = AppScreen.ExercisesSelection.name) {
                ExerciseSelectionScreen(
                    allExercises = displayedExercises,
                    addSelectedExercise = { desc ->
                        // Adding the exercise which is currently selected in ExerciseSelectionScreen
                        viewModel.addExerciseToOngoing(desc)
                        navController.navigate(AppScreen.OngoingWorkout.name)
                    },
                    cancelExerciseSelection = { navController.navigate(AppScreen.OngoingWorkout.name) },
                    onTextChange = viewModel::onSearchTextChange,
                    searchText = searchText
                )
            }

            /* Screen displaying all past workouts */
            composable(route = AppScreen.WorkoutHistory.name) {
                WorkoutHistory(uiState.allWorkouts)
            }

            /* Screen displaying all exercises */
            composable(route = AppScreen.AllExercises.name) {
                AllExercises(
                    allExercises = displayedExercises,
                    onTextChange = viewModel::onSearchTextChange,
                    searchText = searchText,
                )
            }

        }
    }
}