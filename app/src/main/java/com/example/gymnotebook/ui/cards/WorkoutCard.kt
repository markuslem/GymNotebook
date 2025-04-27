package com.example.gymnotebook.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymnotebook.data.DataSource
import com.example.gymnotebook.data.Workout
import com.example.gymnotebook.ui.theme.GymNotebookTheme
import java.util.Date

@Composable
fun WorkoutCard(workout: Workout) {
    Card(modifier = Modifier.padding(10.dp)) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(workout.title, fontWeight = FontWeight.Bold, fontSize = 28.sp)
            Text(
                Date(workout.endDate?.time ?: 0).toString(),
                fontSize = 16.sp
            )
            if (workout.exercises.isNotEmpty()) {
            Text("Total weight: " + workout.totalWeight)
            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                Text(
                    "Exercises:", modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold
                )
                Text("Heaviest set", fontWeight = FontWeight.Bold)
            }
                for (exer in workout.exercises) {
                    Row {
                        Text(exer.desc.name, modifier = Modifier.weight(1f))
                        // Displaying the set with the heaviest weight
                        if (exer.sets.isNotEmpty()) {
                            // TODO: The first set is displayed as the heaviest right now. It should be an attribute.
                            val setInfo = exer.sets[0].weight.toString() + " kg x " + exer.sets[0].reps.toString()
                            Text(setInfo)
                        }
                    }
                }
            } else {
                Text("No exercises found")
            }
        }
    }
}

@Preview
@Composable
fun WorkoutCardPreview() {
    GymNotebookTheme {
        WorkoutCard(
            workout = DataSource.exampleWorkouts.values.toList()[0]
        )
    }
}
