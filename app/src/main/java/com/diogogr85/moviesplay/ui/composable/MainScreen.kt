package com.diogogr85.moviesplay.ui.composable

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
//            BottomNavigation {
//                val navBackStackEntry by navController.currentBackStackEntryAsState()
//                val currentDestination = navBackStackEntry?.destination
//                topLevelRoutes.forEach { topLevelRoute ->
//                    BottomNavigationItem(
//                        icon = { Icon(topLevelRoute.icon, contentDescription = topLevelRoute.name) },
//                        label = { Text(topLevelRoute.name) },
//                        selected = currentDestination?.hierarchy?.any { it.hasRoute(topLevelRoute.route::class) } == true,
//                        onClick = {
//                            navController.navigate(topLevelRoute.route) {
//                                // Pop up to the start destination of the graph to
//                                // avoid building up a large stack of destinations
//                                // on the back stack as users select items
//                                popUpTo(navController.graph.findStartDestination().id) {
//                                    saveState = true
//                                }
//                                // Avoid multiple copies of the same destination when
//                                // reselecting the same item
//                                launchSingleTop = true
//                                // Restore state when reselecting a previously selected item
//                                restoreState = true
//                            }
//                        }
//                    )
//                }
//            }
        }
    ) { innerPadding ->
        NavigationView(navController, innerPadding)
//        NavHost(navController, startDestination = Popular, Modifier.padding(innerPadding)) {
//            composable<Popular> { ProfileScreen(...) }
//            composable<Upcoming> { FriendsScreen(...) }
//        }
    }
}
