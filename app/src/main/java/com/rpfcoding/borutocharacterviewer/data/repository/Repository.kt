package com.rpfcoding.borutocharacterviewer.data.repository

import com.rpfcoding.borutocharacterviewer.domain.repository.MyPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val myPreferencesRepository: MyPreferencesRepository
) {

    suspend fun saveOnBoardingState(completed: Boolean) {
        myPreferencesRepository.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> = myPreferencesRepository.readOnBoardingState()

}