package com.rpfcoding.borutocharacterviewer.presentation.screens.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroEntity
import com.rpfcoding.borutocharacterviewer.domain.use_cases.heroes.SearchHeroesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchHeroesUseCase: SearchHeroesUseCase
) : ViewModel() {

    private var _searchQuery = mutableStateOf("")
    val searchQuery: State<String> get() = _searchQuery

    private val _searchedHeroes = MutableStateFlow<PagingData<HeroEntity>>(PagingData.empty())
    val searchedHeroes: StateFlow<PagingData<HeroEntity>> get() = _searchedHeroes

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchHeroes(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchHeroesUseCase(query = query).cachedIn(viewModelScope).collect {
                _searchedHeroes.value = it
            }
        }
    }

}