package com.binteckh.consultacep.network

data class CepResponse(
    val cep: String?,
    val logradouro: String?,
    val complemento: String?,
    val bairro: String?,
    val localidade: String?,
    val uf: String?,
    val unidade: String?,
    val ibge: String?,
    val gia: String?,
    val ddd: String?,
    val siafi: String?,
    val estado: String?,
    val regiao: String?
)