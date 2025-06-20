package com.example.gymnotebook.ui.screens

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
    onClick: () -> Unit
) {
    Dialog(onDismissRequest = {}) {
        Card(modifier = modifier) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                // Name of exercise and category
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                ) {
                    Text(description.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Row {
                        Text("Category: ", fontSize = 12.sp, fontWeight = FontWeight.Light)
                        Text(description.category, fontSize = 12.sp)
                    }
                    description.force?.let { Text(it, fontSize = 12.sp) }
                    Row {
                        Muscles("Primary Muscles:", description.primaryMuscles)
                        Spacer(Modifier.size(24.dp))
                        Muscles("Primary Muscles:", description.primaryMuscles)
                    }
                }



                Icon(
                    Icons.Default.Close,
                    contentDescription = "More information",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun Muscles(
    description: String,
    musclesList: List<String>
) {
    Column {
        Text(description, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        for (muscle in musclesList) {
            Text(muscle, fontSize = 12.sp)
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
                .fillMaxWidth(),
            description = DataSource.exampleDescriptions[1],
            onClick = {}
        )
    }
}

