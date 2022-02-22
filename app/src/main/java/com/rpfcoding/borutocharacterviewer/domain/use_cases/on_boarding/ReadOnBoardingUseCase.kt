package com.rpfcoding.borutocharacterviewer.domain.use_cases.on_boarding

import com.rpfcoding.borutocharacterviewer.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val repository: Repository
) {

    operator fun invoke(): Flow<Boolean> = repository.readOnBoardingState()

}