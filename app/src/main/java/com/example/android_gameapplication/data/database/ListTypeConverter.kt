package com.example.android_gameapplication.data.database

import androidx.room.TypeConverter

/**
 * Type converter class for converting lists of strings to a single string and vice versa.
 * This class is used by Room to store complex data types in the database.
 */
class ListTypeConverter {

    /**
     * Converts a list of strings to a single comma-separated string.
     *
     * @param value The list of strings to be converted.
     * @return A single string representing the list, with items separated by commas.
     */
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return value.joinToString(",")
    }

    /**
     * Converts a single comma-separated string back into a list of strings.
     *
     * @param value The string to be converted.
     * @return A list of strings, obtained by splitting the input string by commas.
     */
    @TypeConverter
    fun toStringList(value: String): List<String> {
        return value.split(",")
    }
}
