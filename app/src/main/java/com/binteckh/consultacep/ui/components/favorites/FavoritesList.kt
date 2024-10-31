package com.binteckh.consultacep.ui.components.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.binteckh.consultacep.network.CepResponse
import com.binteckh.consultacep.viewmodels.favorites.FavoritesState
import com.binteckh.consultacep.viewmodels.favorites.FavoritesViewModel

@Composable
fun FavoritesList(
    favorites: List<CepResponse>,
    favoritesState: FavoritesState,
    favoritesViewModel: FavoritesViewModel
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(26.dp)
    ) {
        items(favorites) { cepInfo ->
            FavoriteItem(
                cepInfo = cepInfo,
                favoritesState = favoritesState,
                favoritesViewModel = favoritesViewModel
            )
        }
    }
}