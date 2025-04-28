package com.diogogr85.moviesplay.domain.entity

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItem(val name: String, val route: String, val icon: ImageVector) {
    data object Popular : NavItem( POPULAR_MOVIES_TITLE, POPULAR_MOVIES_ROUTE, Icons.Filled.Star)
    data object Upcoming : NavItem(UPCOMING_MOVIES_TITLE, UPCOMING_MOVIES_ROUTE, Icons.Filled.DateRange)

    companion object {
        const val POPULAR_MOVIES_TITLE = "Filmes Populares"
        const val POPULAR_MOVIES_ROUTE = "popular"
        const val UPCOMING_MOVIES_TITLE = "Próximos Lançamentos"
        const val UPCOMING_MOVIES_ROUTE = "upcoming"
    }
}