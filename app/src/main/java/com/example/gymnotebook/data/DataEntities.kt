package com.example.gymnotebook.data


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverters
import java.util.Date
import java.util.UUID

@Entity(tableName = "workouts")
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "start_date") val startDate: Date,
    @ColumnInfo(name = "end_date") var endDate: Date?,
    @ColumnInfo(name = "total_weight") var totalWeight: Int,
)

/* Dataclass for the relationship between WorkoutEntity and ExerciseEntity */
data class WorkoutWithExercises(
    @Embedded
    val workout: WorkoutEntity,

    @Relation(
        parentColumn = "workoutId",    // Primary key of the WorkoutEntity
        entityColumn = "workoutOwnerId", // Foreign key in the ExerciseEntity
        entity = ExerciseEntity::class // Child entity
    )
    val exercises: List<ExerciseEntity>
)

@Entity(tableName = "exercises",
    )
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "desc") val desc: ExerciseDesc,
    @ColumnInfo(name = "sets") var sets: List<SetOfExercise>
)

@Entity(tableName = "set")
data class SetEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "reps") var reps: Int,
    @ColumnInfo(name = "weight") var weight: Float,
    @ColumnInfo(name = "done") var done: Boolean
)

@Entity(tableName = "exercise_desc")
data class ExerciseDescEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "name") val name: String,
    // And more...
)