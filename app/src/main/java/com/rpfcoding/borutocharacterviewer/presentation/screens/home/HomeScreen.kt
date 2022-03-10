package com.rpfcoding.borutocharacterviewer.presentation.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.rpfcoding.borutocharacterviewer.presentation.util.common.ListContent
import com.rpfcoding.borutocharacterviewer.presentation.util.Screen

@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val allHeroes = homeViewModel.heroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar { // onSearchClicked lambda is triggered here.
                navController.navigate(Screen.Search.route)
            }
        },
        content = {
            ListContent(
                navController = navController,
                heroes = allHeroes
            )
        }
    )

}