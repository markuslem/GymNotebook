package com.example.gymnotebook.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.StateFlow

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workouts")
    fun getAllWorkouts(): WorkoutEntity

    @Insert
    fun insertWorkout(workout: WorkoutEntity)
}