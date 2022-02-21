package com.rpfcoding.borutocharacterviewer.data.local

import androidx.room.TypeConverter

class DatabaseConverter {

    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String {
        return list.joinToString(separator = separator)
    }

    @TypeConverter
    fun convertStringToList(str: String): List<String> {
        return str.split(separator)
    }
}