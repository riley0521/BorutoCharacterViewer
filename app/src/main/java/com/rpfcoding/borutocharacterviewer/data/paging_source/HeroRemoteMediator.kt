package com.rpfcoding.borutocharacterviewer.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rpfcoding.borutocharacterviewer.data.local.BorutoDatabase
import com.rpfcoding.borutocharacterviewer.data.remote.BorutoApi
import com.rpfcoding.borutocharacterviewer.domain.model.Hero
import com.rpfcoding.borutocharacterviewer.domain.model.HeroRemoteKey
import com.rpfcoding.borutocharacterviewer.domain.repository.HeroRemoteKeyRepository
import com.rpfcoding.borutocharacterviewer.domain.repository.HeroRepository
import java.lang.Exception
import javax.inject.Inject

@ExperimentalPagingApi
class HeroRemoteMediator @Inject constructor(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase,
    private val heroRepository: HeroRepository,
    private val heroRemoteKeyRepository: HeroRemoteKeyRepository
) : RemoteMediator<Int, Hero>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
        return try {

            val page: Int = when(loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getRemoteKeyClosesToCurrentPosition(state)
                    remoteKey?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    remoteKey?.previousPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKey != null
                    )
                }
                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    remoteKey?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKey != null
                    )
                }
            }

            val response = borutoApi.getAllHeroes(page = page)
            if (response.heroes.isNotEmpty()) {
                borutoDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        heroRepository.deleteAllHeroes()
                        heroRemoteKeyRepository.deleteAllRemoteKeys()
                    }

                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.heroes.map { hero ->
                        HeroRemoteKey(
                            id = hero.id,
                            previousPage = prevPage,
                            nextPage = nextPage
                        )
                    }

                    heroRemoteKeyRepository.addAllRemoteKeys(heroRemoteKeys = keys)
                    heroRepository.addHeroes(heroes = response.heroes)
                }
            }

            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (ex: Exception) {
            MediatorResult.Error(ex)
        }
    }

    private suspend fun getRemoteKeyClosesToCurrentPosition(
        state: PagingState<Int, Hero>
    ): HeroRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                heroRemoteKeyRepository.getRemoteKey(heroId = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Hero>
    ): HeroRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { hero ->
            heroRemoteKeyRepository.getRemoteKey(heroId = hero.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Hero>): HeroRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { hero ->
            heroRemoteKeyRepository.getRemoteKey(heroId = hero.id)
        }
    }
}