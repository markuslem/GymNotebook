package com.example.gymnotebook

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

// Sets per 1 exercise
data class Set(
    var repetitions: Int,
    var weight: Int,
    var done: Boolean
)