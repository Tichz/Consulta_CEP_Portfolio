package com.binteckh.consultacep.viewmodels.search

import com.binteckh.consultacep.network.CepResponse

data class SearchState(
    val cep: String = "",
    val cepInfo: CepResponse? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val message: String? = null
)