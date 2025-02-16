package com.example.gymnotebook.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymnotebook.data.DataSource
import com.example.gymnotebook.ui.cards.WorkoutPlanCard
import com.example.gymnotebook.ui.theme.GymNotebookTheme
import java.util.UUID

/* When the user wants to record a new workout, there are 2 options
1. Quick start - starting a workout without any exercises (exercises can be added during the process)
2. Select a previously composed workout plan.
 */
@Composable
fun RecordWorkoutScreen(
    startWorkout: (UUID) -> Unit,
    quickStartWorkout: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = quickStartWorkout,
                    modifier = Modifier
                        .size(180.dp)
                        .align(Alignment.Center),
                    shape = RoundedCornerShape(32.dp)
                ) {
                    Text(text = "Quick start")
                }
            }
        }

        item {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { println("Would open add workout page") },
                    modifier = Modifier
                        .width(152.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add"
                    )
                }
            }
        }
        workoutCards(startWorkout)
    }

}

fun LazyListScope.workoutCards(startWorkout: (UUID) -> Unit) {
    items(DataSource.workoutPlans) { item ->
        WorkoutPlanCard(
            workoutPlan = item,
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
            startWorkout = startWorkout
        )
    }
}

@Preview
@Composable
fun RecordWorkoutScreenPreview() {
    GymNotebookTheme {
        RecordWorkoutScreen(
            startWorkout = { _ -> },
            quickStartWorkout = { }
        )
    }
}

