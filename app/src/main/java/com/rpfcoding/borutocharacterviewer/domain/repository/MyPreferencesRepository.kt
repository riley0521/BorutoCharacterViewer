package com.rpfcoding.borutocharacterviewer.domain.repository

import kotlinx.coroutines.flow.Flow

interface MyPreferencesRepository {

    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>

}