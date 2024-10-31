package com.binteckh.consultacep.di

import android.content.Context
import androidx.room.Room
import com.binteckh.consultacep.data.CepFavoriteDao
import com.binteckh.consultacep.data.ConsultaCEPDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideConsultaCEPDatabase(@ApplicationContext appContext: Context): ConsultaCEPDatabase {
        return Room.databaseBuilder(
            appContext,
            ConsultaCEPDatabase::class.java,
            "cep_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideCepFavoriteDao(database: ConsultaCEPDatabase): CepFavoriteDao {
        return database.cepFavoriteDao()
    }
}