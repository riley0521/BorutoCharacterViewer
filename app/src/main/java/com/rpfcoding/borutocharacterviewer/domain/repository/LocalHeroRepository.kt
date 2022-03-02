package com.rpfcoding.borutocharacterviewer.domain.repository

import androidx.paging.PagingSource
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroEntity

interface LocalHeroRepository {

    fun getAllHeroes(): PagingSource<Int, HeroEntity>

    fun getSelectedHero(heroId: Int): HeroEntity

    suspend fun addHeroes(heroes: List<HeroEntity>)

    suspend fun deleteAllHeroes()

}