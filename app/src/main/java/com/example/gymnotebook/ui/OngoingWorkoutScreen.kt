package com.example.gymnotebook.ui;

import android.util.Log
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gymnotebook.data.AppUiState
import com.example.gymnotebook.ui.theme.GymNotebookTheme

@Composable
fun OngoingWorkoutScreen(
    modifier: Modifier = Modifier,
    onWeightChanged: (Int, Int, String) -> Unit,
    onRepsChanged: (Int, Int, String) -> Unit,
    uiState: AppUiState
) {
    val exercises = uiState.allExercises ?: emptyList()
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(items = exercises, itemContent = { exercise ->
            Log.d("COMPOSE", "This get rendered ${exercise.name}")
            ExerciseCard(
                exercise = exercise,
                onWeightChanged = onWeightChanged,
                onRepsChanged = onRepsChanged,
                sets = exercise.sets,
                uiState = uiState
            )
        })
    }
}


@Preview
@Composable
fun OngoingWorkoutScreenPreview() {
    GymNotebookTheme {
        OngoingWorkoutScreen(
            modifier = Modifier
                .fillMaxSize(),
            onWeightChanged = { i: Int, i1: Int, s: String -> },
            onRepsChanged = { i: Int, i1: Int, s: String -> },
            uiState = AppUiState()
        )
    }
}