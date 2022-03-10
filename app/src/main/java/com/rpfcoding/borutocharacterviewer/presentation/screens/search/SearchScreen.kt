package com.rpfcoding.borutocharacterviewer.presentation.screens.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.rpfcoding.borutocharacterviewer.presentation.util.common.ListContent

@ExperimentalCoilApi
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val searchQuery by searchViewModel.searchQuery
    val heroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChange = searchViewModel::updateSearchQuery,
                onSearchClicked = searchViewModel::searchHeroes,
                onCloseClicked = navController::popBackStack
            )
        },
        content = {
            ListContent(
                navController = navController,
                heroes = heroes
            )
        }
    )
}