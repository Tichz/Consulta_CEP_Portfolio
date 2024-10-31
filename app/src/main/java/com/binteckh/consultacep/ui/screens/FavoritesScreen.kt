package com.binteckh.consultacep.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.binteckh.consultacep.R
import com.binteckh.consultacep.ui.components.common.ScreenTitle
import com.binteckh.consultacep.ui.components.favorites.Favorites
import com.binteckh.consultacep.ui.components.favorites.FavoritesList
import com.binteckh.consultacep.ui.components.favorites.SearchFilter
import com.binteckh.consultacep.viewmodels.favorites.FavoritesState
import com.binteckh.consultacep.viewmodels.favorites.FavoritesViewModel

@Composable
fun FavoritesScreen(
    favoritesViewModel: FavoritesViewModel,
    modifier: Modifier = Modifier
) {
    val favoritesState = favoritesViewModel.favoritesState.collectAsState().value

    LaunchedEffect(Unit) {
        favoritesViewModel.loadFavorites()
        favoritesViewModel.clearSearchFilter()
    }

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .padding(horizontal = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título da tela
        ScreenTitle(
            title = stringResource(R.string.favoritos),
            topPadding = 32.dp,
            bottomPadding = 16.dp
        )
        // Filtro de pesquisa
        SearchFilter(
            favoritesViewModel = favoritesViewModel,
            favoritesState = favoritesState
        )
        Spacer(modifier = Modifier.height(32.dp))

        // Lista de favoritos
        Favorites(
            favoritesState = favoritesState,
            favoritesViewModel = favoritesViewModel
        )

    }
}