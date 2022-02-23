package com.rpfcoding.borutocharacterviewer.presentation.screens.home

import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navController: NavHostController
) {

    Scaffold(
        topBar = {
            HomeTopBar {

            }
        }
    ) {

    }

}