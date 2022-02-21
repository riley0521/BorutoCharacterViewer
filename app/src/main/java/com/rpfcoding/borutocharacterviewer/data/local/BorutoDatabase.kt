package com.rpfcoding.borutocharacterviewer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rpfcoding.borutocharacterviewer.data.local.dao.HeroDao
import com.rpfcoding.borutocharacterviewer.data.local.dao.HeroRemoteKeyDao
import com.rpfcoding.borutocharacterviewer.domain.model.Hero
import com.rpfcoding.borutocharacterviewer.domain.model.HeroRemoteKey

@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1)
abstract class BorutoDatabase : RoomDatabase() {

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}