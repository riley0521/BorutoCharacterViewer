package com.rpfcoding.borutocharacterviewer.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.rpfcoding.borutocharacterviewer.domain.use_cases.heroes.GetAllHeroesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllHeroesUseCase: GetAllHeroesUseCase
): ViewModel() {

    val heroes = getAllHeroesUseCase()

}