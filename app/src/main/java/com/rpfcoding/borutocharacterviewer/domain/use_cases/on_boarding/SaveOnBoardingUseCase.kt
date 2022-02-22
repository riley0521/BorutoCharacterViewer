package com.rpfcoding.borutocharacterviewer.domain.use_cases.on_boarding

import com.rpfcoding.borutocharacterviewer.data.repository.Repository

class SaveOnBoardingUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed = completed)
    }

}