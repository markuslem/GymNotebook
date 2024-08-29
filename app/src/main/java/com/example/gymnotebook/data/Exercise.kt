package com.example.gymnotebook.data

import java.util.Date

data class DateAndWeight(
    val date: Date,
    val weight: Int
)

data class Exercise(
    val name: String,
    val category: String,
    var datesAndWeights: List<DateAndWeight>? // The user might have never done an exercise
)