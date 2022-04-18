package com.example.movieapp.widgets

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp.data.Movie

@Composable
fun AddToFavourites(
    movie: Movie, isFavourite: Boolean,
    onSaveClick: (Movie) -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp)
    ) {
        when (isFavourite) {
            true -> Icon(imageVector = Icons.Outlined.Favorite, contentDescription = "fav icon",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.clickable {
                    onSaveClick(movie)
                    Log.d("MovieList", "list ${isFavourite}")
                })
            false -> Icon(imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "fav icon",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.clickable {
                    onSaveClick(movie)
                    Log.d("MovieList", "list1 ${isFavourite}")
                })
        }


    }


}