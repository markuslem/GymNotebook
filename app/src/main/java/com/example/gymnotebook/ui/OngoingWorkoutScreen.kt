package com.example.gymnotebook.ui;

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymnotebook.ui.theme.GymNotebookTheme

@Composable
fun OngoingWorkoutScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        //ExerciseCard(exerciseName = )

    }
}




@Preview
@Composable
fun OngoingWorkoutScreenPreview() {
    GymNotebookTheme {
        OngoingWorkoutScreen(
            modifier = Modifier
                .fillMaxSize()
        )
    }
}