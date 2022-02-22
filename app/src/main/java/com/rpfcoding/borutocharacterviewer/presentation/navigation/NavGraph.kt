package com.rpfcoding.borutocharacterviewer.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rpfcoding.borutocharacterviewer.presentation.screens.splash.SplashScreen
import com.rpfcoding.borutocharacterviewer.presentation.screens.welcome.WelcomeScreen
import com.rpfcoding.borutocharacterviewer.util.Constants.DETAILS_ARG_KEY

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Welcome.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navHostController)
        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navHostController)
        }
        composable(route = Screen.Home.route) {

        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(name = DETAILS_ARG_KEY) {
                    type = NavType.IntType
                }
            )
        ) {

        }
        composable(route = Screen.Search.route) {

        }
    }
}