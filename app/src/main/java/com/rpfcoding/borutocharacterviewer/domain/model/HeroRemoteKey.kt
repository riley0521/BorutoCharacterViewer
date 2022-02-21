package com.rpfcoding.borutocharacterviewer.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_hero_remote_keys")
data class HeroRemoteKey(
    @PrimaryKey
    val id: Int,
    val previousPage: Int?,
    val nextPage: Int?
)
