package com.example.expensetracker.utils

import androidx.room.TypeConverter
import java.sql.Date

class DateConverter {
    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value != null) Date(value) else null
    }
}