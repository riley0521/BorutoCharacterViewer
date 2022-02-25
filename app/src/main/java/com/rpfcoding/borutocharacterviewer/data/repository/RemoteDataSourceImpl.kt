package com.rpfcoding.borutocharacterviewer.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rpfcoding.borutocharacterviewer.data.local.BorutoDatabase
import com.rpfcoding.borutocharacterviewer.data.paging_source.HeroRemoteMediator
import com.rpfcoding.borutocharacterviewer.data.remote.BorutoApi
import com.rpfcoding.borutocharacterviewer.domain.model.Hero
import com.rpfcoding.borutocharacterviewer.domain.repository.LocalHeroRemoteKeyRepository
import com.rpfcoding.borutocharacterviewer.domain.repository.LocalHeroRepository
import com.rpfcoding.borutocharacterviewer.domain.repository.RemoteDataSource
import com.rpfcoding.borutocharacterviewer.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase,
    private val localHeroRepository: LocalHeroRepository,
    private val localHeroRemoteKeyRepository: LocalHeroRemoteKeyRepository
) : RemoteDataSource {

    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { localHeroRepository.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase,
                localHeroRepository = localHeroRepository,
                localHeroRemoteKeyRepository = localHeroRemoteKeyRepository
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(): Flow<PagingData<Hero>> {
        TODO("Not yet implemented")
    }
}