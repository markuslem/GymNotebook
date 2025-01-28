package com.example.gymnotebook.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymnotebook.data.DataSource
import com.example.gymnotebook.data.ExerciseDesc
import com.example.gymnotebook.ui.cards.ExerciseSelectionCard
import com.example.gymnotebook.ui.theme.GymNotebookTheme
import java.util.UUID

@Composable
fun ExerciseSelectionScreen(
    modifier: Modifier = Modifier,
    allExercises: HashMap<UUID, ExerciseDesc>? = HashMap(),
    addSelectedExercise: (ExerciseDesc?) -> Unit,
    cancelExerciseSelection: () -> Unit
) {
    // Only one of the radio buttons can be selected at a time
    var selectedExercise by remember { mutableStateOf<ExerciseDesc?>(null) }
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        allExercises?.values?.toTypedArray()?.let { exercises ->
            items(items = exercises, itemContent = { exercise ->
                Log.d("COMPOSE", "This got rendered ${exercise.name}")
                ExerciseSelectionCard(
                    exerciseName = exercise.name,
                    exerciseCategory = exercise.category,
                    isSelected = exercise == selectedExercise, // True if this option is selected
                    onRadioBtnClick = {
                        selectedExercise = exercise
                        Log.d("SELECTION", "$selectedExercise is selected.")
                    }
                )
                Spacer(modifier = Modifier.size(8.dp))
            })
        }
    }
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { addSelectedExercise(selectedExercise) }
        ) { Text("Add Exercise") }
        Spacer(modifier = Modifier.size(20.dp))
        Button(
            onClick = cancelExerciseSelection
        ) { Text("Cancel") }
    }
}

@Preview
@Composable
fun ExerciseSelectionScreenPreview() {
    GymNotebookTheme {
        ExerciseSelectionScreen(allExercises = DataSource.allExercisesHM,
            addSelectedExercise = {}, cancelExerciseSelection = {})
    }
}
