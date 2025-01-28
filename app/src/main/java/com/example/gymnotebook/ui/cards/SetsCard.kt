package com.example.gymnotebook.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymnotebook.data.AppUiState
import com.example.gymnotebook.data.DataSource
import com.example.gymnotebook.data.Exercise
import com.example.gymnotebook.ui.theme.GymNotebookTheme
import com.example.gymnotebook.data.SetOfExercise
import java.util.UUID

/* Displayed in the ongoing workout page */
@Composable
fun ExerciseCard(
    modifier: Modifier = Modifier,
    exercise: Exercise,
    sets: List<SetOfExercise>,
    done: Boolean = false,
    onWeightChanged: (UUID, Int, String) -> Unit,
    onRepsChanged: (UUID, Int, String) -> Unit,
    uiState: AppUiState
) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = exercise.desc.name)

            HorizontalDivider(color = Color.Gray)
            Spacer(modifier = Modifier.size(12.dp))

            Text(text = "Sets:")


            Column( // Every set of the exercise in a column
                modifier = Modifier
            ) {
                sets.forEach { set ->
                    Row() {
                        TextField(
                            modifier = Modifier.width(120.dp),
                            value = set.weight.toString(),
                            onValueChange = { newWeight ->
                                onWeightChanged(exercise.exerciseId, set.id, newWeight)
                            }
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        TextField(
                            modifier = Modifier.width(120.dp),
                            value = set.reps.toString(),
                            onValueChange = { newReps ->
                                onRepsChanged(exercise.exerciseId, set.id, newReps)
                            }
                        )
                        // TODO: add task completed checkbox
                    }
                }
            }

        }
    }

}

@Preview
@Composable
fun ExerciseCardPreview() {
    GymNotebookTheme {
        // Picking an exercise from the workout plans
        val exercise = DataSource.workoutPlans[0].exercisesList?.get(0)
        if (exercise != null) {
            ExerciseCard(
                modifier = Modifier
                    .fillMaxWidth(),
                exercise = exercise,
                sets = exercise.sets,
                uiState = AppUiState(),
                onWeightChanged = { _, _, _ -> },
                onRepsChanged = { _, _, _ -> },
            )
        }
    }
}