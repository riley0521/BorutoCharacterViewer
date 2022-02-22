package com.rpfcoding.borutocharacterviewer.presentation.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rpfcoding.borutocharacterviewer.domain.use_cases.on_boarding.OnBoardingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val onBoardingUseCases: OnBoardingUseCases
): ViewModel() {

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            onBoardingUseCases.saveOnBoardingUseCase(completed = completed)
        }
    }

}