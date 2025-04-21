package com.diogogr85.moviesplay.ui.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.diogogr85.moviesplay.domain.entity.NavItem


@Composable
fun NavigationView(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = "popular", Modifier.padding(innerPadding)) {
        composable(route = NavItem.Popular.route) {
            PopularScreen(savedStateHandle = navController.currentBackStackEntry?.savedStateHandle) { route ->
                navController.navigate(route)
            }
        }
        composable(route = NavItem.Upcoming.route) {
            UpcomingScreen(savedStateHandle = navController.currentBackStackEntry?.savedStateHandle) { route ->
                navController.navigate(route)
            }
        }
    }
}