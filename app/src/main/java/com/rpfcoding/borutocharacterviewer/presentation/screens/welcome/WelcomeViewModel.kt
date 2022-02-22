package com.rpfcoding.borutocharacterviewer.presentation.screens.welcome

import androidx.lifecycle.ViewModel
import com.rpfcoding.borutocharacterviewer.domain.use_cases.on_boarding.OnBoardingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val onBoardingUseCases: OnBoardingUseCases
): ViewModel() {
}