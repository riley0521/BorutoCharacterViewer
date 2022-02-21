package com.rpfcoding.borutocharacterviewer.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_heroes")
data class Hero(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val power: Int,
    val month: String,
    val day: String,
    val family: List<String>,
    val abilities: List<String>,
    val natureTypes: List<String>
)