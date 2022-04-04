package com.example.movieapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.ui.theme.MovieAppTheme


class MainActivity : ComponentActivity() {
    override fun onStart(){
        super.onStart()
        Log.d("OnStartTest", "I am on start")
    }

    override fun onResume(){
        super.onResume()
        Log.d("OnResumeTest", "I am on Resume")
    }

    override fun onPause(){
        super.onPause()
        Log.d("OnPauseTest", "I am on Pause")
    }

    override fun onStop(){
        super.onStop()
        Log.d("onStopTest", "I am on Stop")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d("onDestroyTest", "I am on Destroy")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieApp {
                    MyNavigation()
                }
            }
        }
}

@Composable
fun MovieApp(content: @Composable () -> Unit){
    MovieAppTheme {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppTheme {
        Homescreen()
    }
}