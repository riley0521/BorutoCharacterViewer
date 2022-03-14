package com.rpfcoding.borutocharacterviewer.data.paging_source

import androidx.paging.PagingSource.*
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroEntity
import com.rpfcoding.borutocharacterviewer.data.remote.BorutoApi
import com.rpfcoding.borutocharacterviewer.data.remote.FakeBorutoApi
import com.rpfcoding.borutocharacterviewer.data.remote.dto.HeroDto
import com.rpfcoding.borutocharacterviewer.data.remote.dto.ShinobiRecordDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class SearchHeroesSourceTest {

    private lateinit var borutoApi: BorutoApi
    private lateinit var heroes: List<HeroDto>

    @Before
    fun setup() {
        borutoApi = FakeBorutoApi()
        heroes = listOf(
            HeroDto(
                id = 1,
                englishName = "Sasuke Uchiha",
                japaneseName = "うちはサスケ",
                image = "",
                about = "",
                status = "",
                gender = "",
                age = 33,
                month = "",
                day = "",
                abilities = listOf(),
                heightBasedOnAge = listOf(),
                species = listOf(),
                family = listOf(),
                shinobiRecord = ShinobiRecordDto()
            ),
            HeroDto(
                id = 2,
                englishName = "Naruto Uzumaki",
                japaneseName = "うずまきナルト",
                image = "",
                about = "",
                status = "",
                gender = "",
                age = 33,
                month = "",
                day = "",
                abilities = listOf(),
                heightBasedOnAge = listOf(),
                species = listOf(),
                family = listOf(),
                shinobiRecord = ShinobiRecordDto()
            ),
            HeroDto(
                id = 3,
                englishName = "Sakura Uchiha",
                japaneseName = "うちはサクラ",
                image = "",
                about = "",
                status = "",
                gender = "",
                age = 33,
                month = "",
                day = "",
                abilities = listOf(),
                heightBasedOnAge = listOf(),
                species = listOf(),
                family = listOf(),
                shinobiRecord = ShinobiRecordDto()
            )
        )
    }

    @Test
    fun `Search api with existing hero name, expect single hero result, assert LoadResult_Page`() =
        runBlockingTest {
            val heroSource = SearchHeroesSource(borutoApi = borutoApi, query = "sasuke")

            val firstHero = heroes.first().toHeroEntity()

            assertEquals<LoadResult<Int, HeroEntity>>(
                expected = LoadResult.Page(
                    data = listOf(firstHero),
                    prevKey = null,
                    nextKey = null
                ),
                actual = heroSource.load(
                    params = LoadParams.Refresh(
                        key = null,
                        loadSize = 5,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `Search api with existing hero name, expect multiple hero result, assert LoadResult_Page`() =
        runBlockingTest {
            val heroSource = SearchHeroesSource(borutoApi = borutoApi, query = "sa")

            val multipleHeroes = heroes
                .filter { it.englishName.lowercase().trim().contains("sa") }
                .map { it.toHeroEntity() }

            assertEquals<LoadResult<Int, HeroEntity>>(
                expected = LoadResult.Page(
                    data = multipleHeroes,
                    prevKey = null,
                    nextKey = null
                ),
                actual = heroSource.load(
                    params = LoadParams.Refresh(
                        key = null,
                        loadSize = 5,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `Search api with empty hero name, assert empty heroes list and LoadResult_Page`() =
        runBlockingTest {
            val heroSource = SearchHeroesSource(borutoApi = borutoApi, query = "")

            val loadResult = heroSource.load(
                LoadParams.Refresh(
                    key = null,
                    loadSize = 5,
                    placeholdersEnabled = false
                )
            )

            val result = borutoApi.searchHeroes("").heroes

            assertTrue { result.isEmpty() }
            assertTrue { loadResult is LoadResult.Page }
        }

    @Test
    fun `Search api with non-existing hero name, assert empty heroes list and LoadResult_Page`() =
        runBlockingTest {
            val heroSource = SearchHeroesSource(borutoApi = borutoApi, query = "unknown")

            val loadResult = heroSource.load(
                LoadParams.Refresh(
                    key = null,
                    loadSize = 5,
                    placeholdersEnabled = false
                )
            )

            val result = borutoApi.searchHeroes("unknown").heroes

            assertTrue { result.isEmpty() }
            assertTrue { loadResult is LoadResult.Page }
        }
}
