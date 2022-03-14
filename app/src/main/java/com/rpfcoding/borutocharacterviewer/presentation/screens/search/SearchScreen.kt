package com.rpfcoding.borutocharacterviewer.presentation.screens.search

import android.util.Log
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rpfcoding.borutocharacterviewer.presentation.theme.statusBarColor
import com.rpfcoding.borutocharacterviewer.presentation.util.common.ListContent
import com.rpfcoding.borutocharacterviewer.util.ConnectionState
import com.rpfcoding.borutocharacterviewer.util.ToastManager
import com.rpfcoding.borutocharacterviewer.util.connectivityState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@ExperimentalCoilApi
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val searchQuery by searchViewModel.searchQuery
    val heroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    val connection by connectivityState()
    var isToastShown by remember {
        mutableStateOf(false)
    }

    if (isToastShown) {
        ToastManager(text = "Internet is unavailable.")
        navController.navigateUp()
    }

    val event = searchViewModel.searchScreenEvent

    LaunchedEffect(key1 = event) {
        event.collectLatest { event ->
            when (event) {
                is SearchScreenEvent.SearchResult -> {
                    Log.d("SearchScreen", heroes.itemSnapshotList.items.toString())
                    searchViewModel.saveSearchedHeroes(heroes.itemSnapshotList.items)
                }
            }
        }
    }

    LaunchedEffect(key1 = connection) {
        if (connection == ConnectionState.Unavailable) {
            delay(200L)
            isToastShown = true
        } else {
            isToastShown = false
        }
    }

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
