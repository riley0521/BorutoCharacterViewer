package com.rpfcoding.borutocharacterviewer.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator.MediatorResult
import androidx.test.core.app.ApplicationProvider
import com.rpfcoding.borutocharacterviewer.data.local.BorutoDatabase
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroEntity
import com.rpfcoding.borutocharacterviewer.data.remote.BorutoApi
import com.rpfcoding.borutocharacterviewer.data.remote.FakeBorutoApi2
import com.rpfcoding.borutocharacterviewer.domain.repository.FakeLocalHeroRemoteKeyRepository
import com.rpfcoding.borutocharacterviewer.domain.repository.FakeLocalHeroRepository
import com.rpfcoding.borutocharacterviewer.domain.repository.LocalHeroRemoteKeyRepository
import com.rpfcoding.borutocharacterviewer.domain.repository.LocalHeroRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
class HeroRemoteMediatorTest {

    private lateinit var borutoApi: FakeBorutoApi2
    private lateinit var borutoDatabase: BorutoDatabase
    private lateinit var localHeroRepository: LocalHeroRepository
    private lateinit var localHeroRemoteKeyRepository: LocalHeroRemoteKeyRepository

    @Before
    fun setup() {
        borutoApi = FakeBorutoApi2()
        borutoDatabase = BorutoDatabase.create(
            context = ApplicationProvider.getApplicationContext(),
            useInMemory = true
        )
        localHeroRepository = FakeLocalHeroRepository(borutoDatabase = borutoDatabase)
        localHeroRemoteKeyRepository =
            FakeLocalHeroRemoteKeyRepository(borutoDatabase = borutoDatabase)
    }

    @After
    fun cleanUp() {
        borutoDatabase.clearAllTables()
    }

    @Test
    fun refreshLoad_ReturnsSuccessResult_WhenMoreDataIsPresent() = runBlocking {
        val remoteMediator = HeroRemoteMediator(
            borutoApi = borutoApi,
            borutoDatabase = borutoDatabase,
            localHeroRepository = localHeroRepository,
            localHeroRemoteKeyRepository = localHeroRemoteKeyRepository
        )
        val pagingState = PagingState<Int, HeroEntity>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(pageSize = 5),
            leadingPlaceholderCount = 0
        )
        val result = remoteMediator.load(
            loadType = LoadType.REFRESH,
            state = pagingState
        )

        assertTrue(result is MediatorResult.Success)
        assertFalse((result as MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun refreshLoad_ReturnsSuccessResult_AndEndOfPaginationTrue_WhenNoMoreData() = runBlocking {

        borutoApi.clearData()

        val remoteMediator = HeroRemoteMediator(
            borutoApi = borutoApi,
            borutoDatabase = borutoDatabase,
            localHeroRepository = localHeroRepository,
            localHeroRemoteKeyRepository = localHeroRemoteKeyRepository
        )
        val pagingState = PagingState<Int, HeroEntity>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(pageSize = 5),
            leadingPlaceholderCount = 0
        )
        val result = remoteMediator.load(
            loadType = LoadType.REFRESH,
            state = pagingState
        )

        assertTrue(result is MediatorResult.Success)
        assertTrue((result as MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun refreshLoad_ReturnsErrorResult_WhenErrorOccurs() = runBlocking {

        borutoApi.addException()

        val remoteMediator = HeroRemoteMediator(
            borutoApi = borutoApi,
            borutoDatabase = borutoDatabase,
            localHeroRepository = localHeroRepository,
            localHeroRemoteKeyRepository = localHeroRemoteKeyRepository
        )
        val pagingState = PagingState<Int, HeroEntity>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(pageSize = 5),
            leadingPlaceholderCount = 0
        )
        val result = remoteMediator.load(
            loadType = LoadType.REFRESH,
            state = pagingState
        )

        assertTrue(result is MediatorResult.Error)
    }
}
