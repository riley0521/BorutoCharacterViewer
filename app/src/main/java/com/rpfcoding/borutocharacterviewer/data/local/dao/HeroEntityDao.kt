package com.rpfcoding.borutocharacterviewer.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroEntity

@Dao
interface HeroEntityDao {

    @Query("SELECT * FROM tbl_heroes ORDER BY id ASC")
    fun getAllHeroes(): PagingSource<Int, HeroEntity>

    @Query("SELECT * FROM tbl_heroes WHERE id = :heroId")
    fun getSelectedHero(heroId: Int): HeroEntity

    @Insert(onConflict = REPLACE)
    suspend fun addHeroes(heroes: List<HeroEntity>)

    @Query("DELETE FROM tbl_heroes")
    suspend fun deleteAllHeroes()
}