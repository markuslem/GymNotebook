package com.example.gymnotebook.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymnotebook.data.DataSource
import com.example.gymnotebook.data.WorkoutPlan
import com.example.gymnotebook.ui.theme.GymNotebookTheme

@Composable
fun WorkoutCard(
    modifier: Modifier = Modifier,
    workoutPlan: WorkoutPlan
) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = workoutPlan.title)

            HorizontalDivider(color = Color.Gray)
            Spacer(modifier = Modifier.size(12.dp))


            workoutPlan.exercisesList?.let { exercises ->
                exercises.forEach { exercise ->
                    Text(text = exercise.name)
                }
            } ?: run {
                Text(text = "No exercises",
                    fontStyle = FontStyle.Italic)
            }
        }
    }
}

@Preview
@Composable
fun WorkoutCardPreview() {
    GymNotebookTheme {
        WorkoutCard(
            modifier = Modifier
                .fillMaxWidth(),
            workoutPlan = DataSource.workoutPlans[0]
        )
    }
}