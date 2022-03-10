package com.rpfcoding.borutocharacterviewer.presentation.screens.details

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroEntity
import com.rpfcoding.borutocharacterviewer.domain.use_cases.heroes.GetSelectedHeroUseCase
import com.rpfcoding.borutocharacterviewer.util.Constants.DETAILS_ARG_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getSelectedHeroUseCase: GetSelectedHeroUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedHero = mutableStateOf<HeroEntity?>(null)
    val selectedHero: State<HeroEntity?> = _selectedHero

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val heroId = savedStateHandle.get<Int>(DETAILS_ARG_KEY)
            heroId?.let {
                _selectedHero.value = getSelectedHeroUseCase(heroId = heroId)
                _selectedHero.value?.let { Log.d("Hero", it.toString()) }
            }
        }
    }

}