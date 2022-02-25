package com.rpfcoding.borutocharacterviewer.data.repository

import androidx.paging.PagingSource
import com.rpfcoding.borutocharacterviewer.data.local.dao.HeroDao
import com.rpfcoding.borutocharacterviewer.domain.model.Hero
import com.rpfcoding.borutocharacterviewer.domain.repository.LocalHeroRepository
import javax.inject.Inject

class LocalHeroRepositoryImpl @Inject constructor(
    private val heroDao: HeroDao
) : LocalHeroRepository {

    override fun getAllHeroes(): PagingSource<Int, Hero> =
        heroDao.getAllHeroes()

    override fun getSelectedHero(heroId: Int): Hero =
        heroDao.getSelectedHero(heroId = heroId)

    override suspend fun addHeroes(heroes: List<Hero>) =
        heroDao.addHeroes(heroes = heroes)

    override suspend fun deleteAllHeroes() =
        heroDao.deleteAllHeroes()
}