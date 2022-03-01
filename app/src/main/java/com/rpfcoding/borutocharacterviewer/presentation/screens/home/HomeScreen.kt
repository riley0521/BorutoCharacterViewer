package com.rpfcoding.borutocharacterviewer.presentation.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.rpfcoding.borutocharacterviewer.presentation.common.ListContent

@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val allHeroes = homeViewModel.heroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = {})
        },
        content = {
            ListContent(
                navController = navController,
                heroes = allHeroes
            )
        }
    )

}