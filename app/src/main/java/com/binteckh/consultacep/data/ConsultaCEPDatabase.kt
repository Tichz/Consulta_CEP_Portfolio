package com.binteckh.consultacep.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.binteckh.consultacep.model.CepEntity

@Database(entities = [CepEntity::class], version = 1)
abstract class ConsultaCEPDatabase : RoomDatabase() {
    abstract fun cepFavoriteDao(): CepFavoriteDao
}