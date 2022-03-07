package com.rpfcoding.borutocharacterviewer.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroEntity
import com.rpfcoding.borutocharacterviewer.data.remote.BorutoApi
import com.rpfcoding.borutocharacterviewer.data.remote.dto.HeroDto
import javax.inject.Inject

class SearchHeroesSource @Inject constructor(
    private val borutoApi: BorutoApi,
    private val query: String
) : PagingSource<Int, HeroEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HeroEntity> {
        return try {

            val apiResponse = borutoApi.searchHeroes(name = query)
            val heroes = apiResponse.heroes.map { it.toHeroEntity() }

            if(heroes.isNotEmpty()) {
                LoadResult.Page(
                    data = heroes,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }

        } catch (e: Exception) {
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, HeroEntity>): Int? {
        return state.anchorPosition
    }
}