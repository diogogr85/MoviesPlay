package com.diogogr85.moviesplay.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diogogr85.moviesplay.R
import com.diogogr85.moviesplay.domain.presentation.movies.MoviesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ErrorScreen(viewModel: MoviesViewModel = koinViewModel()) {
    val hasError by ScreenErrorManager.hasError

    if (hasError) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.warning),
                contentDescription = stringResource(id = R.string.content_description_warning),
                modifier = Modifier.width(100.dp),
                alignment = Alignment.Center
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        dimensionResource(R.dimen.padding_small),
                        0.dp,
                        dimensionResource(R.dimen.padding_small),
                        30.dp
                    ),
                text = stringResource(R.string.error_message),
                textAlign = TextAlign.Center
            )

            FilledTonalButton(onClick = {
                viewModel.loadAllMovies()
            }) {
                Text(
                    modifier = Modifier.width(120.dp),
                    text = "Retry",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun ShowError() {
    ErrorScreen()
}