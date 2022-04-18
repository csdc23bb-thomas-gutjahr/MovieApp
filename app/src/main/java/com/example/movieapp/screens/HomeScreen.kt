package com.example.movieapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.data.Movie
import com.example.movieapp.data.getMovies
import com.example.movieapp.module.FavouritesViewModel
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.widgets.AddToFavourites

@Composable
fun Homescreen(navController: NavController = rememberNavController(), favViewModel: FavouritesViewModel) {

    var showMenu by remember {
        mutableStateOf(false)
    }

    val favouritesViewModel: FavouritesViewModel = viewModel()
    favouritesViewModel.favMovies

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Movies") },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "more")
                    }
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(onClick = { navController.navigate(route = MovieScreens.FavouriteScreen.name) }) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "fav icon",
                                    modifier = Modifier.padding(2.dp)
                                )
                                Text(
                                    text = "Favourites", style = MaterialTheme.typography.h6,
                                    modifier = Modifier
                                        .padding(2.dp)
                                        .width(120.dp)
                                )
                            }
                        }
                    }
                }
            )
        }
    ) {
        MainContent(navController = navController, getMovies(), favViewModel)
    }
}

@Composable
fun MainContent(navController: NavController, movies: List<Movie> = getMovies(), favViewModel: FavouritesViewModel) {
    LazyColumn {
        items(items = movies) { movie ->
            MovieRow(showDetails = false, movie = movie, onItemClick = { movieId ->
                navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")
            }) {
                AddToFavourites(movie = movie, isFavourite = favViewModel.movieExists(movie)) {
                    favViewModel.addMovie(movie)
                }
            }
        }
    }
}
