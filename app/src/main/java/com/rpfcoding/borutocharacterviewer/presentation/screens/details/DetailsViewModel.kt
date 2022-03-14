package com.rpfcoding.borutocharacterviewer.presentation.screens.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroEntity
import com.rpfcoding.borutocharacterviewer.domain.use_cases.heroes.GetSelectedHeroUseCase
import com.rpfcoding.borutocharacterviewer.util.Constants.DETAILS_ARG_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getSelectedHeroUseCase: GetSelectedHeroUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedHero: MutableStateFlow<HeroEntity?> = MutableStateFlow(null)
    val selectedHero: StateFlow<HeroEntity?> = _selectedHero

    private val _colorPalette = MutableStateFlow<Map<String, String>>(mapOf())
    val colorPalette: StateFlow<Map<String, String>> = _colorPalette

    private val _event: MutableSharedFlow<UiEvent> = MutableSharedFlow()
    val event: SharedFlow<UiEvent> = _event

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val heroId = savedStateHandle.get<Int>(DETAILS_ARG_KEY)
            heroId?.let {
                _selectedHero.value = getSelectedHeroUseCase(heroId = heroId)
                _selectedHero.value?.let { Log.d("Hero", it.toString()) }
            }
        }
    }

    fun generateColorPalette() {
        viewModelScope.launch {
            _event.emit(UiEvent.GenerateColorPalette)
        }
    }

    fun setColorPalette(colors: Map<String, String>) {
        _colorPalette.value = colors
    }
}

sealed class UiEvent {
    object GenerateColorPalette : UiEvent()
}
