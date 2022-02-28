package com.rpfcoding.borutocharacterviewer.domain.repository

import com.rpfcoding.borutocharacterviewer.domain.model.HeroRemoteKey

interface LocalHeroRemoteKeyRepository {

    suspend fun getRemoteKey(heroId: Int): HeroRemoteKey?

    suspend fun addAllRemoteKeys(heroRemoteKeys: List<HeroRemoteKey>)

    suspend fun deleteAllRemoteKeys()

}