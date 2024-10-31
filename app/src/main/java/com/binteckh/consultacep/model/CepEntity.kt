package com.binteckh.consultacep.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cep_favorites")
data class CepEntity(
    @PrimaryKey val cep: String,
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
    val regiao: String?,
    val timestamp: Long
)