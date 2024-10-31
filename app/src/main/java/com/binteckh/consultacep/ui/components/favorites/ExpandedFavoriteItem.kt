package com.binteckh.consultacep.ui.components.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.binteckh.consultacep.R
import com.binteckh.consultacep.network.CepResponse
import com.binteckh.consultacep.ui.components.common.InfoField
import com.binteckh.consultacep.utils.openMapForLocation

@Composable
fun ExpandedFavoriteItem(
    cepInfo: CepResponse,
    onDelete: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .padding(8.dp)
    ) {
        // Campos de informação detalhada
        InfoField(
            label = "CEP",
            value = cepInfo.cep ?: "Não disponível",
            horizontalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            InfoField(
                label = "CIDADE",
                value = cepInfo.localidade ?: "Não disponível",
                modifier = Modifier.weight(1f)
            )
            InfoField(
                label = "ESTADO",
                value = cepInfo.uf ?: "Não disponível",
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        InfoField(
            label = "LOGRADOURO",
            value = cepInfo.logradouro ?: "Não disponível",
            icon = Icons.Default.Place,
            onIconClick = {
                openMapForLocation(
                    context = context,
                    logradouro = cepInfo.logradouro
                )
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        InfoField(
            label = "BAIRRO",
            value = cepInfo.bairro ?: "Não disponível",
        )
        Spacer(modifier = Modifier.height(8.dp))
        InfoField(
            label = "COMPLEMENTO",
            value = cepInfo.complemento ?: "Não disponível",
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            InfoField(
                label = "CÓDIGO IBGE",
                value = cepInfo.ibge ?: "Não disponível",
                modifier = Modifier.weight(1f)
            )
            InfoField(
                label = "DDD",
                value = cepInfo.ddd ?: "Não disponível",
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(26.dp))
        // Botão de deletar ao final
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiary)
                .clickable { onDelete() },
            Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.delete),
                contentDescription = "Delete Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp)
            )
        }
    }
}