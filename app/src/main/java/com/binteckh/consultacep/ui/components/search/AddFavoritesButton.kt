package com.binteckh.consultacep.ui.components.search

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.binteckh.consultacep.R
import com.binteckh.consultacep.viewmodels.search.SearchState

@Composable
fun AddFavoritesButton(searchState: SearchState, onAddToFavorites: () -> Unit) {
    if (searchState.cepInfo != null) {
        TextButton(
            modifier = Modifier.padding(top = 18.dp),
            onClick = {
                onAddToFavorites()
            }
        ) {
            Text(
                stringResource(R.string.adicionar_aos_favoritos),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp
                ),
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}