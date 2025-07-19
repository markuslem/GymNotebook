package com.example.gymnotebook.data


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.Date
import java.util.UUID

@Entity(tableName = "workouts")
data class WorkoutEntity(
    @PrimaryKey val workoutId: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "start_date") val startDate: Date,
    @ColumnInfo(name = "end_date") var endDate: Date?,
    @ColumnInfo(name = "total_weight") var totalWeight: Int,
//    @ColumnInfo(name = "exercises") var exercises: List<Exercise>
)