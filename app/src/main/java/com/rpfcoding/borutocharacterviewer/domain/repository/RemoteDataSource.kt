package com.rpfcoding.borutocharacterviewer.domain.repository

import androidx.paging.PagingData
import com.rpfcoding.borutocharacterviewer.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllHeroes(): Flow<PagingData<Hero>>

    fun searchHeroes(): Flow<PagingData<Hero>>

}