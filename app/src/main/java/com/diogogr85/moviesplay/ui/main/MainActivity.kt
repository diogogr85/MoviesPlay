package com.diogogr85.moviesplay.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.diogogr85.moviesplay.domain.presentation.movies.MoviesState.Error
import com.diogogr85.moviesplay.domain.presentation.movies.MoviesState.Loading
import com.diogogr85.moviesplay.domain.presentation.movies.MoviesState.Success
import com.diogogr85.moviesplay.domain.presentation.movies.MoviesViewModel
import com.diogogr85.moviesplay.ui.composable.MainScreen
import com.diogogr85.moviesplay.ui.composable.PopularScreen
import com.diogogr85.moviesplay.ui.theme.MoviesPlayTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesPlayTheme {
                MainScreen()
            }
        }
    }
}
