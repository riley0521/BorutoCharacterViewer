package com.rpfcoding.borutocharacterviewer.domain.repository

import com.rpfcoding.borutocharacterviewer.data.local.BorutoDatabase
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroRemoteKeyEntity

class FakeLocalHeroRemoteKeyRepository(
    private val borutoDatabase: BorutoDatabase
) : LocalHeroRemoteKeyRepository {

    private val heroRemoteKeyDao = borutoDatabase.heroRemoteKeyDao()

    override suspend fun getRemoteKey(heroId: Int): HeroRemoteKeyEntity? =
        heroRemoteKeyDao.getRemoteKey(heroId = heroId)

    override suspend fun addAllRemoteKeys(heroRemoteKeyEntities: List<HeroRemoteKeyEntity>) =
        heroRemoteKeyDao.addAllRemoteKeys(heroRemoteKeyEntities = heroRemoteKeyEntities)

    override suspend fun deleteAllRemoteKeys() =
        heroRemoteKeyDao.deleteAllRemoteKeys()
}
