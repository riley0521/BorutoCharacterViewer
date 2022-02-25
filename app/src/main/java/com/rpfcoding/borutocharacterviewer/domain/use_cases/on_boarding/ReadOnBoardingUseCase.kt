package com.rpfcoding.borutocharacterviewer.domain.use_cases.on_boarding

import com.rpfcoding.borutocharacterviewer.domain.repository.MyPreferencesRepository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val repository: MyPreferencesRepository
) {

    operator fun invoke(): Flow<Boolean> = repository.readOnBoardingState()

}