package com.binteckh.consultacep.repository

import com.binteckh.consultacep.data.CepFavoriteDao
import com.binteckh.consultacep.network.CepResponse
import com.binteckh.consultacep.network.ViaCepApi
import com.binteckh.consultacep.utils.toEntity
import com.binteckh.consultacep.utils.toResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CepRepository @Inject constructor(
    private val cepFavoriteDao: CepFavoriteDao
) {

    suspend fun fetchCepInfo(cep: String): CepResponse? {
        return withContext(Dispatchers.IO) {
            try {
                ViaCepApi.service.getCepInfo(cep)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    suspend fun addCepToFavorites(cepResponse: CepResponse) {
        val cepEntity = cepResponse.toEntity().copy(timestamp = System.currentTimeMillis())
        cepFavoriteDao.insertFavorite(cepEntity)
    }

    fun getAllFavorites(): Flow<List<CepResponse>> {
        return cepFavoriteDao.getAllFavorites().map { entities ->
            entities.map { it.toResponse() }
        }
    }

    suspend fun deleteFavorite(cep: String) {
        cepFavoriteDao.deleteFavorite(cep)
    }
}