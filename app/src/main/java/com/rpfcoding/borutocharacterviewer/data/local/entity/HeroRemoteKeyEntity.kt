package com.rpfcoding.borutocharacterviewer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_hero_remote_keys")
data class HeroRemoteKeyEntity(
    @PrimaryKey
    val heroId: Int,
    val previousPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)
