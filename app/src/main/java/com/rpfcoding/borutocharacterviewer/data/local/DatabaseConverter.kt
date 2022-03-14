package com.rpfcoding.borutocharacterviewer.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rpfcoding.borutocharacterviewer.data.remote.dto.ShinobiRecordDto

class DatabaseConverter {

    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String {
        return if (list.isNotEmpty()) {
            list.joinToString(separator = separator)
        } else {
            ""
        }
    }

    @TypeConverter
    fun convertStringToList(str: String): List<String> {
        return str.split(separator)
    }

    @TypeConverter
    fun convertShinobiRecordToJson(shinobiRecordDto: ShinobiRecordDto): String {
        return Gson().toJson(shinobiRecordDto)
    }

    @TypeConverter
    fun convertJsonToShinobiRecord(record: String): ShinobiRecordDto {
        return Gson().fromJson(
            record,
            object : TypeToken<ShinobiRecordDto>() {}.type
        )
    }
}
