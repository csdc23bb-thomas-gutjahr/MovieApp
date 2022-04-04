package com.example.movieapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.data.Movie
import com.example.movieapp.data.getMovies

//@Preview(showBackground = true)
@Composable
fun DetailScreen(navController: NavController = rememberNavController(),
                 movieId : String?) {

    val movie = filterMovie(movieId = movieId)

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

                    Text(text = movie.title)

                }
            }
        }
    ) {
        MainContent(movie = movie)
    }
}

@Composable
fun MainContent(movie: Movie){

    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Column {
            MovieRow(showDetails= true, movie = movie, onItemClick = {})

            Spacer(modifier = Modifier.height(8.dp))

            Divider()

            Text(text = "Movie Images",
                style = MaterialTheme.typography.h5,
                )

            HorizontalScrollImageView(movie = movie)
        }
    }

}

fun filterMovie(movieId: String?): Movie {
    return getMovies().filter { it.id == movieId } [0]
}