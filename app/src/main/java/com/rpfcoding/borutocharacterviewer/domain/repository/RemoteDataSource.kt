package com.rpfcoding.borutocharacterviewer.domain.repository

import androidx.paging.PagingData
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroEntity
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllHeroes(): Flow<PagingData<HeroEntity>>

    fun searchHeroes(): Flow<PagingData<HeroEntity>>

}