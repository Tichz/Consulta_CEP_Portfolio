package com.binteckh.consultacep.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.binteckh.consultacep.model.CepEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CepFavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(cepEntity: CepEntity)

    @Query("SELECT * FROM cep_favorites ORDER BY timestamp DESC")
    fun getAllFavorites(): Flow<List<CepEntity>>

    @Query("DELETE FROM cep_favorites WHERE cep = :cep")
    suspend fun deleteFavorite(cep: String)
}
