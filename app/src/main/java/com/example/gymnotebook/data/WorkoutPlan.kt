package com.example.gymnotebook.data

data class WorkoutPlan(
    var title: String,
    var exercisesList: List<Exercise>? // User does not have to specify which exercises are done
)