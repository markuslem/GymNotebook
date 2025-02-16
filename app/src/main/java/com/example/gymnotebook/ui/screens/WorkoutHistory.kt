package com.example.gymnotebook.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymnotebook.data.DataSource
import com.example.gymnotebook.data.Workout
import com.example.gymnotebook.ui.cards.ExerciseCard
import com.example.gymnotebook.ui.cards.WorkoutCard
import com.example.gymnotebook.ui.theme.GymNotebookTheme
import java.util.UUID

/* Consists of workout WorkoutCards */
@Composable
fun WorkoutHistory(
    workouts: HashMap<UUID, Workout>
) {
    // Displaying all workouts as clickable cards

    LazyColumn {
        items(items = workouts.values.toList(), itemContent = { workout ->
            Log.d("COMPOSE", "This got rendered $workout")
            WorkoutCard(workout)
            Spacer(modifier = Modifier.size(8.dp))
        }
        )
    }
}

@Preview
@Composable
fun ExerciseCardPreview() {
    GymNotebookTheme {
        WorkoutHistory(DataSource.exampleWorkouts)
    }
}
