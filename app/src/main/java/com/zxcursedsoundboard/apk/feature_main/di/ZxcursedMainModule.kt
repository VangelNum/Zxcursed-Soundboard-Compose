package com.zxcursedsoundboard.apk.feature_main.di

import com.zxcursedsoundboard.apk.feature_main.data.ZxcursedMainRepositoryImpl
import com.zxcursedsoundboard.apk.feature_main.domain.ZxcursedMainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ZxcursedMainModule {
    @Binds
    @Singleton
    abstract fun bindZxcursedRepository(
        zxcursedMainRepositoryImpl: ZxcursedMainRepositoryImpl
    ): ZxcursedMainRepository
}