package com.example.gymnotebook.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.gymnotebook.data.DataSource
import com.example.gymnotebook.data.ExerciseDesc
import com.example.gymnotebook.ui.theme.GymNotebookTheme

@Composable
fun DetailedDescriptionCard(
    modifier: Modifier, description: ExerciseDesc,
    onClose: () -> Unit
) {
    Dialog(onDismissRequest = {}) {
        Card {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                // Name of exercise and category
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                ) {
                    // General information
                    Text(description.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Row {
                        Text("Category: ", fontSize = 16.sp)
                        Text(
                            description.category,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    description.force?.let { Text("Force: $it", fontSize = 16.sp) }


                }

                IconButton(
                    onClick = onClose
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "More information",
                        modifier = Modifier.size(24.dp),
                    )
                }
            }

            // Primary and Secondary Muscles in 2 columns
            Row (modifier = Modifier.padding(start = 24.dp, end = 24.dp)) {
                Muscles("Primary Muscles:", description.primaryMuscles, modifier = Modifier.weight(1f))
                Spacer(Modifier.size(24.dp))
                Muscles("Secondary Muscles:", description.primaryMuscles, modifier = Modifier.weight(1f))
            }

            // Instructions
            LazyColumn (modifier = Modifier.padding(24.dp)){
                items(items = description.instructions) {
                    Text(it)
                }
            }
        }
    }
}

@Composable
fun Muscles(
    description: String,
    musclesList: List<String>,
    modifier: Modifier = Modifier
) {
    Column (modifier = modifier) {
        Text(description, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        for (muscle in musclesList) {
            Text(muscle, fontSize = 16.sp)
        }
    }
}

@Preview
@Composable
fun DetailedDescriptionCardPreview() {
    GymNotebookTheme {
        // Picking an exercise from the workout plans
        DetailedDescriptionCard(
            modifier = Modifier
                .fillMaxSize(),
            description = DataSource.exampleDescriptions[1],
            onClose = {}
        )
    }
}

