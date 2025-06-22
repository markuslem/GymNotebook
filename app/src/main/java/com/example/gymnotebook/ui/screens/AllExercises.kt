package com.example.gymnotebook.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymnotebook.data.DataSource
import com.example.gymnotebook.data.ExerciseDesc
import com.example.gymnotebook.data.allExercisesToHM
import com.example.gymnotebook.ui.theme.GymNotebookTheme
import java.util.UUID

@Composable
fun AllExercises(
    allExercises: Map<UUID, ExerciseDesc> = HashMap(),
    onTextChange: (String) -> Unit,
    searchText: String = ""
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val selectedDesc = remember { mutableStateOf(UUID.randomUUID()) }
    var textLocally = remember { mutableStateOf("") }

    // Alert pops up when the right conditions are met
    when {
        openAlertDialog.value -> {
            allExercises[selectedDesc.value]?.let {
                DetailedDescriptionCard(
                    modifier = Modifier,
                    description = it,
                    onClose = {
                        openAlertDialog.value = false
                    })
            }
        }
    }

    Column(
        modifier = Modifier.padding(16.dp)
    )
    {
        // Search bar
        TextField(
            value = searchText,
            onValueChange = onTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Search") }
        )

        Spacer(Modifier.size(16.dp))

        // Scrollable column containing exercise descriptions
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            allExercises.values.toTypedArray().let { exercises ->
                items(items = exercises, itemContent = { exercise ->
                    Log.d("COMPOSE", "This got rendered ${exercise.name}")
                    DescriptionCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        description = exercise,
                        onClick = {
                            openAlertDialog.value = true
                            selectedDesc.value = exercise.descId
                            Log.d(
                                "WORKOUT",
                                "Exercise description card was expanded using UUID: " + selectedDesc.value
                            )
                        }
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                })
            }
        }
    }
}

@Preview
@Composable
fun AddExercisesPreview() {
    GymNotebookTheme {
        AllExercises(
            allExercises = allExercisesToHM(DataSource.exampleDescriptions),
            onTextChange = { }
        )
    }
}
