package com.multimodule.data.di

import android.content.Context
import com.multimodule.data.cache.AppDatabase
import com.multimodule.data.cache.SatelliteDao
import com.multimodule.data.repository.SatelliteRepositoryImpl
import com.multimodule.domain.repository.SatelliteGateRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by bedirhansaricayir on 6.12.2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.buildDatabase(context)


    @Provides
    @Singleton
    fun provideSatelliteDao(appDatabase: AppDatabase): SatelliteDao =
        appDatabase.satelliteDao()
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideGateAway(satelliteRepositoryImpl: SatelliteRepositoryImpl): SatelliteGateRepository
}
