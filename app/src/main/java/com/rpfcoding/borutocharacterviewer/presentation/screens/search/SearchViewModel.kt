package com.rpfcoding.borutocharacterviewer.presentation.screens.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

) : ViewModel() {

    private var _searchQuery = mutableStateOf("")
    val searchQuery: State<String> get() = _searchQuery

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

}