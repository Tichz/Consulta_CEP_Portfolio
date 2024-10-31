package com.binteckh.consultacep.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.binteckh.consultacep.R
import com.binteckh.consultacep.ui.components.common.ScreenTitle
import com.binteckh.consultacep.ui.components.search.AddFavoritesButton
import com.binteckh.consultacep.ui.components.search.CepInfoDisplay
import com.binteckh.consultacep.ui.components.search.LoadingZipCode
import com.binteckh.consultacep.ui.components.search.SearchButton
import com.binteckh.consultacep.ui.components.search.SearchTextField
import com.binteckh.consultacep.viewmodels.search.SearchViewModel

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel,
    modifier: Modifier
) {
    val homeState = searchViewModel.searchState.collectAsState().value
    val context = LocalContext.current


    // Limpa o CEP quando a tela buscar é acessada
    LaunchedEffect(Unit) {
        searchViewModel.clearCep()
    }

    // Observa mudanças na mensagem e exibe o Toast
    LaunchedEffect(homeState.message) {
        homeState.message?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            // Após exibir o Toast, limpa a mensagem no ViewModel
            searchViewModel.clearMessage()
        }
    }

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 26.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título acima do campo de entrada
        ScreenTitle(
            title = stringResource(R.string.digite_o_cep),
            topPadding = 72.dp,
            bottomPadding = 28.dp
        )

        // Campo de entrada do CEP
        SearchTextField(
            searchViewModel = searchViewModel,
            searchState = homeState
        )

        // Botão de buscar
        SearchButton(
            onClick = {
                searchViewModel.searchCep()
            }
        )

        // Indicador de carregamento
        LoadingZipCode(searchState = homeState)

        // Exibição de detalhes do CEP ou mensagem de erro.
        CepInfoDisplay(homeState)

        // Botão para adicionar ao favorito
        AddFavoritesButton(
            searchState = homeState,
            onAddToFavorites = {
                searchViewModel.addToFavorites()
            }
        )
    }
}