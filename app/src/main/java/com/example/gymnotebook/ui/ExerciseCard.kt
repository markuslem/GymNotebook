package com.example.gymnotebook.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymnotebook.ui.theme.GymNotebookTheme
import com.example.gymnotebook.Set
import com.example.gymnotebook.data.AppUiState

@Composable
fun ExerciseCard(
    modifier: Modifier = Modifier,
    exerciseName: String,
    sets: List<Set> = emptyList(),
    done: Boolean = false,
    onWeightChanged: (String) -> Unit = {},
) {
    Card(modifier = modifier) {
        var weightValue by remember {mutableStateOf("1000") }

        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = exerciseName)

            HorizontalDivider(color = Color.Gray)
            Spacer(modifier = Modifier.size(12.dp))

            Text(text = "Sets:")
            /*sets.let { sets ->
                sets.forEach( set ->
                    Text(text = set.weight)
                )
            }*/

            Row(
                modifier = Modifier
            ) {
                sets.forEach {

                    TextField(
                        value = weightValue,
                        onValueChange = {
                            onWeightChanged(it)
                            weightValue = it
                        }
                    )

                }
            }

        }
    }

}

@Preview
@Composable
fun ExerciseCardPreview() {
    var setsExample = listOf(
        Set(80, 10, false),
        Set(100, 4, true)
    )

    GymNotebookTheme {
        ExerciseCard(
            modifier = Modifier
                .fillMaxWidth(),
            exerciseName = "Squat",
            sets = setsExample,
        )
    }
}