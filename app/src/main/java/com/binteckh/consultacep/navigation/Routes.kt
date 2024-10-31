package com.binteckh.consultacep.navigation

sealed class Routes(val route: String) {
    data object Search : Routes("search")
    data object Favorites : Routes("favorites")
}