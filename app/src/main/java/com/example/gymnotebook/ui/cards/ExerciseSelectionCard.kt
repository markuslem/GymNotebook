package com.example.gymnotebook.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymnotebook.ui.theme.GymNotebookTheme

@Composable
fun ExerciseSelectionCard(
    modifier: Modifier = Modifier,
    exerciseName: String,
    exerciseCategory: String,
    isSelected: Boolean,
    onRadioBtnClick: () -> Unit
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Name of exercise and category
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(exerciseName, fontSize = 24.sp)
                Text(exerciseCategory, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            RadioButton(
                selected = isSelected,
                onClick = onRadioBtnClick,
                modifier = Modifier.scale(1.5f)
            )
        }
    }
}

@Preview
@Composable
fun ExerciseSelectionCardPreview() {
    GymNotebookTheme {
        // Picking an exercise from the workout plans
        ExerciseSelectionCard(
            modifier = Modifier
                .fillMaxWidth(),
            exerciseName = "Example exercise",
            exerciseCategory = "Example category",
            isSelected = false,
            onRadioBtnClick = {}
        )
    }
}
