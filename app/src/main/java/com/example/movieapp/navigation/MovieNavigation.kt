package com.example.movieapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.module.FavouritesViewModel
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.screens.FavouriteScreen
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MyNavigation() {
    val navController = rememberNavController()
    val favouritesViewModel: FavouritesViewModel = viewModel()

    NavHost(navController = navController, startDestination = MovieScreens.Homescreen.name) {

        composable(MovieScreens.Homescreen.name) { Homescreen(navController = navController, favouritesViewModel) }

        composable(
            MovieScreens.DetailScreen.name + "/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen(
                navController = navController,
                movieId = backStackEntry.arguments?.getString("movieId"),
                favouritesViewModel
            )
        }
        composable(MovieScreens.FavouriteScreen.name) { FavouriteScreen(navController = navController, favouritesViewModel) }
    }
}