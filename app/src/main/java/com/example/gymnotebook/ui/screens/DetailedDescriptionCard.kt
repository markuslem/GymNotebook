package com.example.gymnotebook.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
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
import com.example.gymnotebook.data.DataSource
import com.example.gymnotebook.data.ExerciseDesc
import com.example.gymnotebook.ui.theme.GymNotebookTheme

@Composable
fun DetailedDescriptionCard(modifier: Modifier, description: ExerciseDesc) {
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
                Text(description.category, fontSize = 12.sp)
                description.force?.let { Text(it, fontSize = 12.sp) }
                Text(description.level, fontSize = 12.sp)
            }
            Spacer(Modifier.size(12.dp))
            Icon(
                Icons.Default.Info,
                contentDescription = "More information",
                modifier = Modifier.size(24.dp)
            )
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
            description = DataSource.exampleDescriptions[0],
        )
    }
}

