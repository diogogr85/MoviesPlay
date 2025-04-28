package com.diogogr85.moviesplay.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.diogogr85.moviesplay.R
import com.diogogr85.moviesplay.ui.theme.Pink80

@Composable
fun LoadingScreen() {
    val isLoading by ScreenLoadingManager.isLoading

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize()//.padding(innerPadding)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(dimensionResource(R.dimen.width_large))
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }
}

@Preview
@Composable
fun ShowLoading() {
    LoadingScreen()
}