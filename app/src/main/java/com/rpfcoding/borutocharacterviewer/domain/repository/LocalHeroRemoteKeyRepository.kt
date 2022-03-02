package com.rpfcoding.borutocharacterviewer.domain.repository

import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroRemoteKeyEntity

interface LocalHeroRemoteKeyRepository {

    suspend fun getRemoteKey(heroId: Int): HeroRemoteKeyEntity?

    suspend fun addAllRemoteKeys(heroRemoteKeyEntities: List<HeroRemoteKeyEntity>)

    suspend fun deleteAllRemoteKeys()

}