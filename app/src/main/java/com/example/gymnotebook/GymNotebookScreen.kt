package com.example.gymnotebook

import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gymnotebook.ui.RecordWorkoutScreen

enum class AppScreen (barTitle: String) {
    RecordWorkout(barTitle = "Record a new workout"),
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

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
    val uiState by viewModel.uiState.collectAsState()
        RecordWorkoutScreen(modifier = Modifier.padding(innerPadding))
    }
}