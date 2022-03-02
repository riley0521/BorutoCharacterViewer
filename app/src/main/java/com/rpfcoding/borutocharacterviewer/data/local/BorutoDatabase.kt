package com.rpfcoding.borutocharacterviewer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rpfcoding.borutocharacterviewer.data.local.dao.HeroEntityDao
import com.rpfcoding.borutocharacterviewer.data.local.dao.HeroRemoteKeyDao
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroEntity
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroRemoteKeyEntity

@Database(
    entities = [HeroEntity::class, HeroRemoteKeyEntity::class],
    version = 1,
)
@TypeConverters(DatabaseConverter::class)
abstract class BorutoDatabase : RoomDatabase() {

    abstract fun heroEntityDao(): HeroEntityDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}