package com.binteckh.consultacep.ui.components.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.binteckh.consultacep.R
import com.binteckh.consultacep.ui.components.common.InfoField
import com.binteckh.consultacep.utils.openMapForLocation
import com.binteckh.consultacep.viewmodels.search.SearchState

@Composable
fun CepInfoDisplay(searchState: SearchState) {

    val context = LocalContext.current

    searchState.errorMessage?.let { errorMessage ->
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.error
        )
    } ?: searchState.cepInfo?.let { cepInfo ->
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.secondary)
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            InfoField(
                label = stringResource(R.string.cep_consultado),
                value = cepInfo.cep ?: stringResource(R.string.nao_disponivel),
                horizontalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                InfoField(
                    label = stringResource(R.string.cidade),
                    value = cepInfo.localidade ?: stringResource(R.string.nao_disponivel),
                    modifier = Modifier.weight(1f)
                )
                InfoField(
                    label = stringResource(R.string.estado),
                    value = cepInfo.uf ?: stringResource(R.string.nao_disponivel),
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            InfoField(
                label = stringResource(R.string.logradouro),
                value = cepInfo.logradouro ?: stringResource(R.string.nao_disponivel),
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
                label = stringResource(R.string.bairro),
                value = cepInfo.bairro ?: stringResource(R.string.nao_disponivel),
            )
            Spacer(modifier = Modifier.height(8.dp))
            InfoField(
                label = stringResource(R.string.complemento),
                value = cepInfo.complemento ?: stringResource(R.string.nao_disponivel),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                InfoField(
                    label = stringResource(R.string.codigo_ibge),
                    value = cepInfo.ibge ?: stringResource(R.string.nao_disponivel),
                    modifier = Modifier.weight(1f)
                )
                InfoField(
                    label = stringResource(R.string.ddd),
                    value = cepInfo.ddd ?: stringResource(R.string.nao_disponivel),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}