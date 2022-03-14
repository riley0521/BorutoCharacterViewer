package com.rpfcoding.borutocharacterviewer.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {
        fun create(context: Context, useInMemory: Boolean): BorutoDatabase {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, BorutoDatabase::class.java)
            } else {
                Room.databaseBuilder(context, BorutoDatabase::class.java, "test_db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun heroEntityDao(): HeroEntityDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}
