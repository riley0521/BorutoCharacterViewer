package com.rpfcoding.borutocharacterviewer.domain.repository

import androidx.paging.PagingSource
import com.rpfcoding.borutocharacterviewer.domain.model.Hero

interface HeroRepository {

    fun getAllHeroes(): PagingSource<Int, Hero>

    fun getSelectedHero(heroId: Int): Hero

    suspend fun addHeroes(heroes: List<Hero>)

    suspend fun deleteAllHeroes()

}