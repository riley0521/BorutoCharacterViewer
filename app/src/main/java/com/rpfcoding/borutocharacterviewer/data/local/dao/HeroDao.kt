package com.rpfcoding.borutocharacterviewer.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.rpfcoding.borutocharacterviewer.domain.model.Hero

@Dao
interface HeroDao {

    @Query("SELECT * FROM tbl_heroes ORDER BY id ASC")
    fun getAllHeroes(): PagingSource<Int, Hero>

    @Query("SELECT * FROM tbl_heroes WHERE id = :heroId")
    fun getSelectedHero(heroId: Int): Hero

    @Insert(onConflict = REPLACE)
    suspend fun addHeroes(heroes: List<Hero>)

    @Query("DELETE FROM tbl_heroes")
    suspend fun deleteAllHeroes()
}