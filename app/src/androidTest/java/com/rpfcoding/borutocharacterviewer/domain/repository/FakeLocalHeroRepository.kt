package com.rpfcoding.borutocharacterviewer.domain.repository

import androidx.paging.PagingSource
import com.rpfcoding.borutocharacterviewer.data.local.BorutoDatabase
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroEntity

class FakeLocalHeroRepository(
    private val borutoDatabase: BorutoDatabase
) : LocalHeroRepository {

    private val heroDao = borutoDatabase.heroEntityDao()

    override fun getAllHeroes(): PagingSource<Int, HeroEntity> =
        heroDao.getAllHeroes()

    override fun getSelectedHero(heroId: Int): HeroEntity =
        heroDao.getSelectedHero(heroId = heroId)

    override suspend fun addHeroes(heroes: List<HeroEntity>) =
        heroDao.addHeroes(heroes = heroes)

    override suspend fun deleteAllHeroes() =
        heroDao.deleteAllHeroes()
}
