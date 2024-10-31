package com.binteckh.consultacep.viewmodels.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binteckh.consultacep.network.CepResponse
import com.binteckh.consultacep.repository.CepRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val cepRepository: CepRepository
) : ViewModel() {

    private val _favoritesState = MutableStateFlow(FavoritesState())
    val favoritesState: StateFlow<FavoritesState> = _favoritesState

    private var allFavorites: List<CepResponse> = emptyList()

    // Carrega os favoritos do repositório
    fun loadFavorites() {
        viewModelScope.launch {
            cepRepository.getAllFavorites()
                .catch { exception ->
                    _favoritesState.update {
                        it.copy(errorMessage = "Erro ao carregar favoritos: ${exception.message}")
                    }
                }
                .collectLatest { favorites ->
                    allFavorites = favorites
                    applyFilter()
                }
        }
    }

    // Atualiza o filtro de pesquisa
    fun updateSearchFilter(newSearchFilter: String) {
        _favoritesState.update {
            it.copy(searchFilter = newSearchFilter)
        }
        applyFilter()
    }

    // Limpa o filtro de pesquisa
    fun clearSearchFilter() {
        _favoritesState.update {
            it.copy(searchFilter = "")
        }
        applyFilter()
    }

    // Aplica o filtro de pesquisa
    private fun applyFilter() {
        val filter = _favoritesState.value.searchFilter.lowercase()

        val filteredFavorites = if (filter.isBlank()) {
            allFavorites
        } else {
            allFavorites.filter { cepResponse ->
                val bairro = cepResponse.bairro?.lowercase() ?: ""
                val logradouro = cepResponse.logradouro?.lowercase() ?: ""
                val cep = cepResponse.cep?.lowercase() ?: ""

                bairro.contains(filter) || logradouro.contains(filter) || cep.contains(filter)
            }
        }

        _favoritesState.update {
            it.copy(favorites = filteredFavorites)
        }
    }

    // Remove um favorito
    fun removeFavorite(cep: String) {
        viewModelScope.launch {
            cepRepository.deleteFavorite(cep)
            allFavorites = allFavorites.filterNot { it.cep == cep }
            applyFilter()
        }
    }

    // Altera o estado de expansão de um item
    fun toggleItemExpansion(cep: String) {
        _favoritesState.update { currentState ->
            val isCurrentlyExpanded = currentState.expandedItemCep == cep
            val newExpandedItemCep = if (isCurrentlyExpanded) {
                null
            } else {
                cep
            }
            currentState.copy(expandedItemCep = newExpandedItemCep)
        }
    }
}