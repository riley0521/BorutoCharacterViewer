package com.rpfcoding.borutocharacterviewer.data.repository

import com.rpfcoding.borutocharacterviewer.data.local.dao.HeroRemoteKeyDao
import com.rpfcoding.borutocharacterviewer.domain.model.HeroRemoteKey
import com.rpfcoding.borutocharacterviewer.domain.repository.HeroRemoteKeyRepository
import javax.inject.Inject

class HeroRemoteKeyRepositoryImpl @Inject constructor(
    private val heroRemoteKeyDao: HeroRemoteKeyDao
) : HeroRemoteKeyRepository {

    override suspend fun getRemoteKey(id: Int): HeroRemoteKey? =
        heroRemoteKeyDao.getRemoteKey(id = id)

    override suspend fun addAllRemoteKeys(heroRemoteKeys: List<HeroRemoteKey>) =
        heroRemoteKeyDao.addAllRemoteKeys(heroRemoteKeys = heroRemoteKeys)

    override suspend fun deleteAllRemoteKeys() =
        heroRemoteKeyDao.deleteAllRemoteKeys()
}