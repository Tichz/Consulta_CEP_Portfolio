package com.binteckh.consultacep.viewmodels.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binteckh.consultacep.repository.CepRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val cepRepository: CepRepository
) : ViewModel() {
    private val _searchState = MutableStateFlow(SearchState())
    val searchState: StateFlow<SearchState> = _searchState

    // Método para adicionar CEP aos favoritos
    fun addToFavorites() {
        val cepInfo = _searchState.value.cepInfo
        if (cepInfo != null) {
            viewModelScope.launch {
                try {
                    cepRepository.addCepToFavorites(cepInfo)
                    _searchState.value = _searchState.value.copy(
                        message = "CEP adicionado aos favoritos."
                    )
                } catch (e: Exception) {
                    _searchState.value = _searchState.value.copy(
                        message = "Erro ao adicionar CEP aos favoritos."
                    )
                }
            }
        } else {
            _searchState.value = _searchState.value.copy(
                message = "Nenhum CEP disponível para adicionar."
            )
        }
    }

    // Método para limpar a mensagem após exibida
    fun clearMessage() {
        _searchState.value = _searchState.value.copy(message = null)
    }

    fun updateCep(newCep: String) {
        _searchState.value = _searchState.value.copy(cep = newCep)
    }

    fun clearCep() {
        _searchState.value = _searchState.value.copy(cep = "")
    }

    // Método para buscar o CEP na API
    fun searchCep() {
        viewModelScope.launch {
            val cep = _searchState.value.cep.filter { it.isDigit() }
            if (cep.length != 8) {
                _searchState.value = _searchState.value.copy(
                    errorMessage = "Por favor, insira um CEP válido com 8 dígitos."
                )
                return@launch
            }
            // Limpa os dados anteriores e inicia o carregamento
            _searchState.value = _searchState.value.copy(
                isLoading = true,
                errorMessage = null,
                cepInfo = null
            )
            val cepInfo = cepRepository.fetchCepInfo(cep)

            if (cepInfo == null || cepInfo.logradouro.isNullOrEmpty()) {
                _searchState.value = _searchState.value.copy(
                    cepInfo = null,
                    isLoading = false,
                    errorMessage = "Problema ao buscar o CEP, verifique sua conexão, ou o CEP digitado e tente novamente."
                )
            } else {
                _searchState.value = _searchState.value.copy(
                    cepInfo = cepInfo,
                    isLoading = false,
                    errorMessage = null
                )
            }
        }
    }
}