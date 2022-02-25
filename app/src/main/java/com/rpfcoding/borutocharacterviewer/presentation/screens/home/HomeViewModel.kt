package com.rpfcoding.borutocharacterviewer.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.rpfcoding.borutocharacterviewer.domain.use_cases.heroes.HeroesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val heroesUseCases: HeroesUseCases
): ViewModel() {

    val heroes = heroesUseCases.getAllHeroesUseCase()

}