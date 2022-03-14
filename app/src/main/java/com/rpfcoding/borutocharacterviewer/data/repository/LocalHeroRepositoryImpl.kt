package com.rpfcoding.borutocharacterviewer.data.repository

import androidx.paging.PagingSource
import com.rpfcoding.borutocharacterviewer.data.local.dao.HeroEntityDao
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroEntity
import com.rpfcoding.borutocharacterviewer.domain.repository.LocalHeroRepository
import javax.inject.Inject

class LocalHeroRepositoryImpl @Inject constructor(
    private val heroEntityDao: HeroEntityDao
) : LocalHeroRepository {

    override fun getAllHeroes(): PagingSource<Int, HeroEntity> =
        heroEntityDao.getAllHeroes()

    override fun getSelectedHero(heroId: Int): HeroEntity =
        heroEntityDao.getSelectedHero(heroId = heroId)

    override suspend fun addHeroes(heroes: List<HeroEntity>) =
        heroEntityDao.addHeroes(heroes = heroes)

    override suspend fun deleteAllHeroes() =
        heroEntityDao.deleteAllHeroes()
}
