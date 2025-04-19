package com.diogogr85.moviesplay.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.SavedStateHandle
import coil3.compose.AsyncImage
import com.diogogr85.moviesplay.BuildConfig
import com.diogogr85.moviesplay.R
import com.diogogr85.moviesplay.domain.entity.Movie
import com.diogogr85.moviesplay.domain.presentation.movies.MoviesViewModel
import com.diogogr85.moviesplay.ui.theme.Pink80
import com.diogogr85.moviesplay.ui.theme.Purple40
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularScreen(
    viewModel: MoviesViewModel = koinViewModel(),
    savedStateHandle: SavedStateHandle? = null,
    navigate: (String) -> Unit
) {
    val movies: List<Movie>? by viewModel.popularMoviesListData.observeAsState(emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Pink80,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(stringResource(R.string.top_app_bar_popular))
                }
            )
        },
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding
        ) {
            items(movies ?: emptyList()) { movie ->
                    MovieCard(movie = movie)
            }
        }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Purple40
        ),
        modifier = Modifier
            .padding(
                start = dimensionResource(R.dimen.padding_small),
                top = dimensionResource(R.dimen.padding_extra_small),
                end = dimensionResource(R.dimen.padding_small),
                bottom = dimensionResource(R.dimen.padding_extra_small)
            )
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.CenterVertically),
        elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.card_elevation_extra_small)),

        ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncImage(
                model = "${BuildConfig.BASE_IMAGE_URL}${movie.posterPath}",
                contentDescription = movie.originalTitle
            )

            Column(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_small))
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = movie.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}