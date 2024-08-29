package com.example.gymnotebook.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymnotebook.ui.theme.GymNotebookTheme
import androidx.compose.ui.Modifier
import com.example.gymnotebook.data.DataSource

@Composable
fun RecordWorkoutScreen(
    modifier: Modifier = Modifier,
) {
    Column(modifier = Modifier) {

        Button(
            onClick = { /*TODO: Navigate to a new Screen*/ },
            modifier = Modifier
                .width(152.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add"
            )
        }


        LazyColumn {
            items(DataSource.workoutPlans) { item ->
                WorkoutCard(
                    workoutPlan = item,
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun RecordWorkoutScreenPreview() {
    GymNotebookTheme {
        RecordWorkoutScreen(
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

