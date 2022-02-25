package com.rpfcoding.borutocharacterviewer.domain.use_cases.heroes

import androidx.paging.PagingData
import com.rpfcoding.borutocharacterviewer.domain.model.Hero
import com.rpfcoding.borutocharacterviewer.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase(
    private val repository: RemoteDataSource
) {

    operator fun invoke(): Flow<PagingData<Hero>> {
        return repository.getAllHeroes()
    }

}