package com.binteckh.consultacep.ui.components.favorites

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.binteckh.consultacep.R
import com.binteckh.consultacep.network.CepResponse
import com.binteckh.consultacep.ui.components.common.InfoField
import com.binteckh.consultacep.viewmodels.favorites.FavoritesState
import com.binteckh.consultacep.viewmodels.favorites.FavoritesViewModel

@Composable
fun FavoriteItem(
    cepInfo: CepResponse,
    favoritesState: FavoritesState,
    favoritesViewModel: FavoritesViewModel
) {
    val isExpanded = cepInfo.cep == favoritesState.expandedItemCep

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .clickable {
                cepInfo.cep?.let { favoritesViewModel.toggleItemExpansion(it) }
            }
            .animateContentSize()
    ) {
        if (!isExpanded) {
            // Exibir o preview do item
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    InfoField(
                        label = "CEP",
                        value = cepInfo.cep ?: "Não disponível",
                    )
                    InfoField(
                        label = "LOGRADOURO",
                        value = cepInfo.logradouro ?: "Não disponível",
                    )
                }
                // Botão de deletar no preview
                Box(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .size(width = 43.dp, height = 95.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.delete),
                        contentDescription = "Delete Icon",
                        tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                cepInfo.cep?.let { favoritesViewModel.removeFavorite(it) }
                            }
                    )
                }
            }
        } else {
            // Exibir o item expandido
            ExpandedFavoriteItem(
                cepInfo = cepInfo,
                onDelete = {
                    cepInfo.cep?.let { favoritesViewModel.removeFavorite(it) }
                }
            )
        }
    }
}