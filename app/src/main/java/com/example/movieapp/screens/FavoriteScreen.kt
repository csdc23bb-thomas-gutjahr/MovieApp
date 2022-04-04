package com.example.movieapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.MovieRow
import com.example.movieapp.data.Movie
import com.example.movieapp.data.getMovies
import com.example.movieapp.navigation.MovieScreens

@Composable
fun FavoriteScreen(navController: NavController = rememberNavController()){

    var showMenu by remember{
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(elevation = 3.dp) {
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                    })

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(text = "Favorites")
                }
            }
        }
    ) {
        MainContent(navController = navController)
    }
}

@Composable
fun MainContent(navController: NavController, movies: List<Movie> = getMovies()){
    LazyColumn {
        items(items = movies) { movie ->
            MovieRow(showDetails = false, movie = movie, onItemClick = { movieId ->
                navController.navigate(route = MovieScreens.DetailScreen.name+"/$movieId")
            })
        }
    }
}