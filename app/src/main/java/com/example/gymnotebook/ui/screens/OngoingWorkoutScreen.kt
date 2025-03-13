package com.example.gymnotebook.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymnotebook.data.DataSource
import com.example.gymnotebook.data.Exercise
import com.example.gymnotebook.data.ExerciseDesc
import com.example.gymnotebook.ui.cards.ExerciseCard
import com.example.gymnotebook.ui.theme.GymNotebookTheme
import java.util.UUID

@Composable
fun OngoingWorkoutScreen(
    modifier: Modifier = Modifier.padding(24.dp), // TODO: Maybe it should have default value `Modifier`
    onWeightChanged: (UUID, UUID, Float) -> Unit,
    onRepsChanged: (UUID, UUID, Int) -> Unit,
    onDoneChanged: (UUID, UUID, Boolean) -> Unit,
    onWorkoutFinished: () -> Unit,
    exercises: List<Exercise>,
    chooseExercise: () -> Unit,
    addSet: (UUID) -> Unit,
) {
    /* Displaying all exercises with their sets (weights and reps included) from the currently
    selected precomposed workout.
    In the future
                  TODO: new sets can be removed when the workout is in progress
    */
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = exercises, itemContent = { exercise ->
            Log.d("COMPOSE", "This got rendered ${exercise.desc.name}")
            ExerciseCard(
                exercise = exercise,
                sets = exercise.sets,
                onWeightChanged = onWeightChanged,
                onRepsChanged = onRepsChanged,
                onDoneChanged = onDoneChanged,
                addSet = addSet,
            )
            Spacer(modifier = Modifier.size(8.dp))
        }
        )
        // Buttons at the bottom of the page
        items(items = listOf(1), itemContent = {
            Row(modifier = Modifier.padding(8.dp)) {
                Button(
                    onClick = chooseExercise,
                    modifier = Modifier,
                ) {
                    Text("Add exercise")
                }
                Spacer(Modifier.size(12.dp))
                Button(
                    onClick = onWorkoutFinished,
                    modifier = Modifier,
                ) {
                    Text("Finish workout")
                }
            }
        })
    }
}


@Preview
@Composable
fun OngoingWorkoutScreenPreview() {
    GymNotebookTheme {
        OngoingWorkoutScreen(
            onWeightChanged = { _: UUID, _: UUID, _: Float -> },
            onRepsChanged = { _: UUID, _: UUID, _: Int -> },
            onDoneChanged = { _, _, _ -> },
            onWorkoutFinished = {},
            exercises = DataSource.workoutPlansHM.values.toList()[0].exercisesList,
            chooseExercise = {},
            addSet = {},
        )
    }
}