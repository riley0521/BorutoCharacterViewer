package com.rpfcoding.borutocharacterviewer.data.repository

import com.rpfcoding.borutocharacterviewer.data.local.dao.HeroRemoteKeyDao
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroRemoteKeyEntity
import com.rpfcoding.borutocharacterviewer.domain.repository.LocalHeroRemoteKeyRepository
import javax.inject.Inject

class LocalHeroRemoteKeyRepositoryImpl @Inject constructor(
    private val heroRemoteKeyDao: HeroRemoteKeyDao
) : LocalHeroRemoteKeyRepository {

    override suspend fun getRemoteKey(heroId: Int): HeroRemoteKeyEntity? =
        heroRemoteKeyDao.getRemoteKey(heroId = heroId)

    override suspend fun addAllRemoteKeys(heroRemoteKeyEntities: List<HeroRemoteKeyEntity>) =
        heroRemoteKeyDao.addAllRemoteKeys(heroRemoteKeyEntities = heroRemoteKeyEntities)

    override suspend fun deleteAllRemoteKeys() =
        heroRemoteKeyDao.deleteAllRemoteKeys()
}