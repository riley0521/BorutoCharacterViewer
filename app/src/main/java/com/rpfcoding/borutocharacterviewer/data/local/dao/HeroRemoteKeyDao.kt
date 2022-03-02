package com.rpfcoding.borutocharacterviewer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroRemoteKeyEntity

@Dao
interface HeroRemoteKeyDao {

    @Query("SELECT * FROM tbl_hero_remote_keys WHERE heroId = :heroId")
    suspend fun getRemoteKey(heroId: Int): HeroRemoteKeyEntity?

    @Insert(onConflict = REPLACE)
    suspend fun addAllRemoteKeys(heroRemoteKeyEntities: List<HeroRemoteKeyEntity>)

    @Query("DELETE FROM tbl_hero_remote_keys")
    suspend fun deleteAllRemoteKeys()

}