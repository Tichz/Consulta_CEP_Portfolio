package com.binteckh.consultacep.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.binteckh.consultacep.ui.screens.FavoritesScreen
import com.binteckh.consultacep.ui.screens.SearchScreen
import com.binteckh.consultacep.viewmodels.favorites.FavoritesViewModel
import com.binteckh.consultacep.viewmodels.search.SearchViewModel

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.Search.route,
    modifier: Modifier
) {
    val searchViewModel: SearchViewModel = hiltViewModel()
    val favoritesViewModel: FavoritesViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = Routes.Search.route) {
            SearchScreen(
                searchViewModel = searchViewModel,
                modifier = modifier
            )
        }
        composable(route = Routes.Favorites.route) {
            FavoritesScreen(
                favoritesViewModel = favoritesViewModel,
                modifier = modifier
            )
        }
    }
}