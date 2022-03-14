package com.rpfcoding.borutocharacterviewer.presentation.screens.home

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rpfcoding.borutocharacterviewer.presentation.theme.statusBarColor
import com.rpfcoding.borutocharacterviewer.presentation.util.Screen
import com.rpfcoding.borutocharacterviewer.presentation.util.common.ListContent

@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val allHeroes = homeViewModel.heroes.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

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