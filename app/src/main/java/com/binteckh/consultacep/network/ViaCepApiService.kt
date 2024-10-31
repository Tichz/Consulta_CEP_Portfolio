package com.binteckh.consultacep.network

import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepApiService {
    @GET("{cep}/json/")
    suspend fun getCepInfo(@Path("cep") cep: String): CepResponse
}