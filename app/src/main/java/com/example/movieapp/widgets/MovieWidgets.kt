package com.example.movieapp

import android.widget.HorizontalScrollView
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.movieapp.data.Movie
import com.example.movieapp.data.getMovies

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(showDetails: Boolean, movie: Movie, onItemClick: (String) -> Unit){
    var showDetails by remember{
        mutableStateOf(showDetails)
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp

    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Surface(
                modifier = Modifier
                    .size(120.dp)
                    .padding(12.dp),
                elevation = 6.dp
            ){
                //Icon(imageVector = Icons.Default.AccountBox , contentDescription ="movie image" )
                Image(
                    painter = rememberImagePainter(
                        data = movie.images[0],
                        builder = {
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = "Movie poster",
                    modifier = Modifier.size(10.dp)
                    )
            }
            Column(
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            ) {
                Text(text =movie.title, style= MaterialTheme.typography.h6)
                Text(text ="Director: ${movie.director}", style= MaterialTheme.typography.body2)
                Text(text ="Released: ${movie.year}", style= MaterialTheme.typography.body2)
                AnimatedVisibility(visible = showDetails,
                    enter = fadeIn(),
                    exit = slideOutHorizontally() + shrinkVertically() + fadeOut()
                ) {
                    MovieDetails(movie)
                }
                when(showDetails){
                    true -> Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "arrow down", modifier = Modifier.clickable { showDetails =! showDetails  })
                    false-> Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "arrow up", modifier = Modifier.clickable { showDetails =! showDetails  })
                }
            }
        }
    }
}

@Composable
fun HorizontalScrollImageView(movie: Movie = getMovies()[0]) {
    LazyRow {
        items(movie.images) { image ->

            Card (
                modifier = Modifier.padding(12.dp).size(240.dp),
                elevation = 4.dp
                    ){
                Image(
                    painter = rememberImagePainter(data = image),
                    contentDescription = "movie image"
                )
            }

        }
    }
}

@Composable
fun MovieDetails(movie: Movie){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)) {
        Column() {
            Text(text ="Plot: ${movie.plot}", style= MaterialTheme.typography.body2)
            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(top =2.dp, bottom = 2.dp))
            Text(text ="Genre: ${movie.genre}", style= MaterialTheme.typography.body2)
            Text(text ="Actor: ${movie.actors}", style= MaterialTheme.typography.body2)
            Text(text ="Rating: ${movie.rating}", style= MaterialTheme.typography.body2)
        }
    }
}