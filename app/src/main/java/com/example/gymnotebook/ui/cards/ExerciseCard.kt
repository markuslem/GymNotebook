package com.example.gymnotebook.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymnotebook.data.DataSource
import com.example.gymnotebook.data.Exercise
import com.example.gymnotebook.data.SetOfExercise
import com.example.gymnotebook.ui.theme.GymNotebookTheme
import java.util.Collections
import java.util.UUID

/* Displayed in the ongoing workout page */
@Composable
fun ExerciseCard(
    modifier: Modifier = Modifier,
    exercise: Exercise,
    sets: List<SetOfExercise>,
    onWeightChanged: (UUID, UUID, Float) -> Unit,
    onRepsChanged: (UUID, UUID, Int) -> Unit,
    onDoneChanged: (UUID, UUID, Boolean) -> Unit,
    addSet: (UUID) -> Unit
) {
    val doublePattern = Regex("^\\d+\\.?\\d*?$") // Accepts inputs in the formats 0 0. and 0.0
    val integerPattern = Regex("^\\d{1,6}$")
    val weightValueStrs =
        remember { mutableStateListOf(*Array(sets.size) { sets[it].weight.toString() }) }
    // Weight is valid if it got updated in the viewmodel the last time the user updated the value
    val isWeightValid = remember { mutableStateListOf(*Array(sets.size) { true }) }

    val repsValueStrs =
        remember { mutableStateListOf(*Array(sets.size) { sets[it].reps.toString() }) }
    val isRepsValid = remember { mutableStateListOf(*Array(sets.size) { true }) }


    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = exercise.desc.name)

            HorizontalDivider(color = Color.Gray)
            Spacer(modifier = Modifier.size(12.dp))

            Text(text = "Sets:")


            Column( // Every set of the exercise in a column
                modifier = Modifier
            ) {
                sets.forEachIndexed { index, set ->
                    Row {
                        // Whether the checkbox is checked or not
                        var checked by remember { mutableStateOf(false) }
                        TextField(
                            modifier = Modifier.width(120.dp),
                            value = weightValueStrs[index],
                            onValueChange = { newWeight ->
                                // Checking if the entered weight value is valid (can be converted to double)
                                // In case of an empty string the weight value will not get updated in viewmodel
                                val inAcceptableFormat = doublePattern.matches(newWeight)
                                checked = false // Since value is changed
                                onDoneChanged(exercise.exerciseId, set.id, checked)
                                if (inAcceptableFormat) {
                                    onWeightChanged(
                                        exercise.exerciseId,
                                        set.id,
                                        newWeight.toFloat()
                                    )
                                    isWeightValid[index] = true
                                } else {
                                    isWeightValid[index] = false
                                }
                                weightValueStrs[index] = newWeight
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        TextField(
                            modifier = Modifier.width(120.dp),
                            value = repsValueStrs[index],
                            onValueChange = { newReps ->

                                val inAcceptableFormat = integerPattern.matches(newReps)
                                checked = false // Since value is changed
                                onDoneChanged(exercise.exerciseId, set.id, checked)

                                if (inAcceptableFormat) {
                                    onRepsChanged(
                                        exercise.exerciseId,
                                        set.id,
                                        newReps.toInt()
                                    )
                                    isRepsValid[index] = true
                                } else {
                                    isRepsValid[index] = false
                                }
                                repsValueStrs[index] = newReps
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        // Task completed checkbox
                        Checkbox(modifier = Modifier
                            .scale(1.5f)
                            .padding(start = 16.dp, top = 8.dp),
                            checked = checked, onCheckedChange =
                            {
                                if (isRepsValid[index] && isWeightValid[index]) {
                                    checked = it
                                    onDoneChanged(exercise.exerciseId, set.id, it)
                                }
                            })
                    }
                }


                /* Add new set to the exercise */
                Spacer(modifier = Modifier.size(20.dp))
                Button(
                    onClick = {
                        addSet(exercise.exerciseId)
                        // Updating elements that keep track of the user inputs
                        weightValueStrs.add("")
                        isWeightValid.add(false)
                        repsValueStrs.add("")
                        isRepsValid.add(false)
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Add Set")
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
        val exercise = DataSource.workoutPlans[0].exercisesList?.first()
        if (exercise != null) {
            ExerciseCard(
                modifier = Modifier
                    .fillMaxWidth(),
                exercise = exercise,
                sets = exercise.sets,
                onWeightChanged = { _, _, _ -> },
                onRepsChanged = { _, _, _ -> },
                onDoneChanged = { _, _, _ -> },
                addSet = { _ -> },
            )
        }
    }
}