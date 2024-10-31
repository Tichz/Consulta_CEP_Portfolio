package com.binteckh.consultacep.ui.components.search

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.binteckh.consultacep.R
import com.binteckh.consultacep.viewmodels.search.SearchState

@Composable
fun LoadingZipCode(searchState: SearchState) {
    if (searchState.isLoading) {
        Text(
            stringResource(R.string.buscando_cep),
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp),
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}