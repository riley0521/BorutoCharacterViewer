package com.rpfcoding.borutocharacterviewer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rpfcoding.borutocharacterviewer.data.remote.dto.ShinobiRecordDto

@Entity(tableName = "tbl_heroes")
data class HeroEntity(
    @PrimaryKey
    val id: Int,
    val englishName: String,
    val japaneseName: String,
    val image: String,
    val about: String,
    val status: String,
    val gender: String,
    val age: Int,
    val month: String,
    val day: String,
    val abilities: List<String> = listOf(),
    val heightBasedOnAge: List<String> = listOf(),
    val species: List<String> = listOf(),
    val family: List<String> = listOf(),
    val shinobiRecord: ShinobiRecordDto = ShinobiRecordDto()
)
