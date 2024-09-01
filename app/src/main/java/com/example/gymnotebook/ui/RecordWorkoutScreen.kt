package com.example.gymnotebook.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymnotebook.ui.theme.GymNotebookTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import com.example.gymnotebook.data.DataSource

@Composable
fun RecordWorkoutScreen(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { /*TODO*/ },
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
            }
        }
        WorkoutCards()
    }

}

fun LazyListScope.WorkoutCards() {
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

