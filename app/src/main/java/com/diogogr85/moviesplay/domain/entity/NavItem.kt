package com.diogogr85.moviesplay.domain.entity

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItem(val name: String, val route: String, val icon: ImageVector) {
    object Popular : NavItem( "Filmes Populares","popular", Icons.Filled.Star)
    object Upcoming : NavItem("Próximos Lançamentos","upcoming", Icons.Filled.DateRange)
}