package com.example.movieapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.MovieRow
import com.example.movieapp.module.FavouritesViewModel
import com.example.movieapp.navigation.MovieScreens

@Composable
fun FavouriteScreen(navController: NavController, favViewModel : FavouritesViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                    }
                    Text(text = "My Favourite Movies", style = MaterialTheme.typography.h6)
                }
            }
        }) {

        val movieList = favViewModel.favMovies
        if(!movieList.isEmpty()){
            LazyColumn() {
                items(movieList) { item ->
                    MovieRow(false, item, onItemClick = { movieId ->
                        navController.navigate(route = "${MovieScreens.DetailScreen}/$movieId")
                    })

                }

            }
        }
        else{
            Card(
                modifier = Modifier.fillMaxWidth()
                    .padding(top= 40.dp, start = 20.dp, end = 20.dp)
                    .height(200.dp),
                backgroundColor = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(corner = CornerSize(16.dp))
                ,

                ) {
                Box(Modifier.wrapContentSize(Alignment.Center)) {
                    Text(
                        text = "No Favourites selected",
                        style = MaterialTheme.typography.body2
                    )
                }
            }

        }

    }

}