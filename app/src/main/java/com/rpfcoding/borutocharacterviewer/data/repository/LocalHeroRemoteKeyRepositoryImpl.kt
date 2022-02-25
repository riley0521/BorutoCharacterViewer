package com.rpfcoding.borutocharacterviewer.data.repository

import com.rpfcoding.borutocharacterviewer.data.local.dao.HeroRemoteKeyDao
import com.rpfcoding.borutocharacterviewer.domain.model.HeroRemoteKey
import com.rpfcoding.borutocharacterviewer.domain.repository.LocalHeroRemoteKeyRepository
import javax.inject.Inject

class LocalHeroRemoteKeyRepositoryImpl @Inject constructor(
    private val heroRemoteKeyDao: HeroRemoteKeyDao
) : LocalHeroRemoteKeyRepository {

    override suspend fun getRemoteKey(id: Int): HeroRemoteKey? =
        heroRemoteKeyDao.getRemoteKey(id = id)

    override suspend fun addAllRemoteKeys(heroRemoteKeys: List<HeroRemoteKey>) =
        heroRemoteKeyDao.addAllRemoteKeys(heroRemoteKeys = heroRemoteKeys)

    override suspend fun deleteAllRemoteKeys() =
        heroRemoteKeyDao.deleteAllRemoteKeys()
}