package com.example.gymnotebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gymnotebook.ui.screens.GymNotebookApp
import com.example.gymnotebook.ui.theme.GymNotebookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            GymNotebookTheme {
                GymNotebookApp()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GymNotebookTheme {
        GymNotebookApp()
    }
}