package com.rpfcoding.borutocharacterviewer.presentation.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.rpfcoding.borutocharacterviewer.presentation.components.RatingWidget
import com.rpfcoding.borutocharacterviewer.ui.theme.LARGE_PADDING

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val allHeroes = homeViewModel.heroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = {})
        }
    ) {
        RatingWidget(modifier = Modifier.padding(all = LARGE_PADDING), rating = 4.5)
    }

}