package com.binteckh.consultacep.ui.components.favorites

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.binteckh.consultacep.R
import com.binteckh.consultacep.viewmodels.favorites.FavoritesState
import com.binteckh.consultacep.viewmodels.favorites.FavoritesViewModel

@Composable
fun Favorites(
    favoritesState: FavoritesState,
    favoritesViewModel: FavoritesViewModel
) {
    if (favoritesState.errorMessage != null) {
        Text(
            text = favoritesState.errorMessage,
            color = MaterialTheme.colorScheme.error
        )
    } else if (favoritesState.favorites.isEmpty()) {
        Text(stringResource(R.string.nenhum_favorito_encontrado))
    } else {
        FavoritesList(
            favorites = favoritesState.favorites,
            favoritesState = favoritesState,
            favoritesViewModel = favoritesViewModel
        )
    }
}