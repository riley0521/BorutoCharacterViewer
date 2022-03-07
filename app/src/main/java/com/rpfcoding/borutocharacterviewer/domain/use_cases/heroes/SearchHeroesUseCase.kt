package com.rpfcoding.borutocharacterviewer.domain.use_cases.heroes

import androidx.paging.PagingData
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroEntity
import com.rpfcoding.borutocharacterviewer.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(
    private val repository: RemoteDataSource
) {

    operator fun invoke(query: String): Flow<PagingData<HeroEntity>> {
        return repository.searchHeroes(query = query)
    }

}