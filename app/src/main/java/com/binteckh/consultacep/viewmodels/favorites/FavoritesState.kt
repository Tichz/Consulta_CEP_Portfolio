package com.binteckh.consultacep.viewmodels.favorites

import com.binteckh.consultacep.network.CepResponse

data class FavoritesState(
    val favorites: List<CepResponse> = emptyList(),
    val errorMessage: String? = null,
    val searchFilter: String = "",
    val expandedItemCep: String? = null
)