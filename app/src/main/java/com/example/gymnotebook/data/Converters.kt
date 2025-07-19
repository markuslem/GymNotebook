package com.example.gymnotebook.data

import androidx.room.TypeConverter
import java.util.Date
import kotlin.text.format

class Converters {
    /* Date converters */
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}