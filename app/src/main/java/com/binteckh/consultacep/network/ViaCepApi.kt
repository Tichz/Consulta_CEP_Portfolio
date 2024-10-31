package com.binteckh.consultacep.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ViaCepApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://viacep.com.br/ws/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: ViaCepApiService = retrofit.create(ViaCepApiService::class.java)
}