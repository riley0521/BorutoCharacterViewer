package com.rpfcoding.borutocharacterviewer.domain.use_cases.on_boarding

import com.rpfcoding.borutocharacterviewer.domain.repository.MyPreferencesRepository

class SaveOnBoardingUseCase(
    private val repository: MyPreferencesRepository
) {

    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed = completed)
    }

}