package com.binteckh.consultacep.utils

import com.binteckh.consultacep.model.CepEntity
import com.binteckh.consultacep.network.CepResponse

fun CepResponse.toEntity(timestamp: Long = System.currentTimeMillis()): CepEntity {
    return CepEntity(
        cep = this.cep ?: "",
        logradouro = this.logradouro,
        complemento = this.complemento,
        bairro = this.bairro,
        localidade = this.localidade,
        uf = this.uf,
        unidade = this.unidade,
        ibge = this.ibge,
        gia = this.gia,
        ddd = this.ddd,
        siafi = this.siafi,
        estado = this.estado,
        regiao = this.regiao,
        timestamp = timestamp
    )
}

fun CepEntity.toResponse(): CepResponse {
    return CepResponse(
        cep = this.cep,
        logradouro = this.logradouro,
        complemento = this.complemento,
        bairro = this.bairro,
        localidade = this.localidade,
        uf = this.uf,
        unidade = this.unidade,
        ibge = this.ibge,
        gia = this.gia,
        ddd = this.ddd,
        siafi = this.siafi,
        estado = this.estado,
        regiao = this.regiao
    )
}