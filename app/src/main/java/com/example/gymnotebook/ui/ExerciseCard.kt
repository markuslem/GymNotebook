package com.example.gymnotebook.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

@Composable
fun ExerciseCard(
    modifier: Modifier = Modifier,
    exercise: Exercise,
    sets: List<SetOfExercise>,
    done: Boolean = false,
    onWeightChanged: (Int, Int, String) -> Unit,
    onRepsChanged: (Int, Int, String) -> Unit,
    uiState: AppUiState
) {
    Card(modifier = modifier) {
        var weightValue by remember { mutableStateOf("1000") }
        var repetitions by remember { mutableStateOf("4") }

        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = exercise.name)

            HorizontalDivider(color = Color.Gray)
            Spacer(modifier = Modifier.size(12.dp))

            Text(text = "Sets:")
            /*sets.let { sets ->
                sets.forEach( set ->
                    Text(text = set.weight)
                )
            }*/

            Column( // Every set of the exercise in a column
                modifier = Modifier
            ) {
                sets.forEach { set ->
                    Row() {
                        TextField(
                            value = set.weight.toString(),
                            onValueChange = { newWeight ->
                                onWeightChanged(exercise.id, set.id, newWeight)
                            }
                        )
                        TextField(
                            value = set.reps.toString(),
                            onValueChange = { newReps ->
                                onRepsChanged(exercise.id, set.id, newReps)
                            }
                        )
                    }
                }
            }

        }
    }

}

@Preview
@Composable
fun ExerciseCardPreview() {
//    var setsExample = listOf(
//        SetOfExercise(80, 10.0F, false),
//        SetOfExercise(100, 4.0F, true)
//    )

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