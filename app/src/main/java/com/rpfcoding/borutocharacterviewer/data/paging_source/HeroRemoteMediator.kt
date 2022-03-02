package com.rpfcoding.borutocharacterviewer.data.paging_source

import android.annotation.SuppressLint
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rpfcoding.borutocharacterviewer.data.local.BorutoDatabase
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroEntity
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroRemoteKeyEntity
import com.rpfcoding.borutocharacterviewer.data.remote.BorutoApi
import com.rpfcoding.borutocharacterviewer.domain.repository.LocalHeroRemoteKeyRepository
import com.rpfcoding.borutocharacterviewer.domain.repository.LocalHeroRepository
import javax.inject.Inject

@ExperimentalPagingApi
class HeroRemoteMediator @Inject constructor(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase,
    private val localHeroRepository: LocalHeroRepository,
    private val localHeroRemoteKeyRepository: LocalHeroRemoteKeyRepository
) : RemoteMediator<Int, HeroEntity>() {

    @SuppressLint("NewApi")
    override suspend fun initialize(): InitializeAction {

        // Current time based on the time of the android OS
        val currentTime = System.currentTimeMillis()

        // Current time based on the server time when the first set of data is fetched.
        val lastUpdated = localHeroRemoteKeyRepository.getRemoteKey(heroId = 1)?.lastUpdated ?: 0L

        // 24 hours is 1440 minutes
        val cacheTimeoutInMinutes = 1440

        // (currentTime - lastUpdated) will return milliseconds.
        // Then, divide 1000 to get seconds.
        // Then, divide 60 to get minutes
        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60

        // Compare diffInMinutes and cacheTimeoutInMinutes
        // to determine whether to fetch new data from server or not.
        return if(diffInMinutes <= cacheTimeoutInMinutes) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, HeroEntity>): MediatorResult {
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
                        localHeroRepository.deleteAllHeroes()
                        localHeroRemoteKeyRepository.deleteAllRemoteKeys()
                    }

                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.heroes.map { hero ->
                        HeroRemoteKeyEntity(
                            heroId = hero.id,
                            previousPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = response.lastUpdated
                        )
                    }

                    localHeroRemoteKeyRepository.addAllRemoteKeys(heroRemoteKeyEntities = keys)
                    localHeroRepository.addHeroes(heroes = response.heroes.map { it.toHeroEntity() })
                }
            }

            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (ex: Exception) {
            MediatorResult.Error(ex)
        }
    }

    private suspend fun getRemoteKeyClosesToCurrentPosition(
        state: PagingState<Int, HeroEntity>
    ): HeroRemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                localHeroRemoteKeyRepository.getRemoteKey(heroId = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, HeroEntity>
    ): HeroRemoteKeyEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { hero ->
            localHeroRemoteKeyRepository.getRemoteKey(heroId = hero.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, HeroEntity>): HeroRemoteKeyEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { hero ->
            localHeroRemoteKeyRepository.getRemoteKey(heroId = hero.id)
        }
    }
}