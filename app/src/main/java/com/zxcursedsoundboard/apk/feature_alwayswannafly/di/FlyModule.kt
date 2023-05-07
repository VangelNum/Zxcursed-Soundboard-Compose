package com.zxcursedsoundboard.apk.feature_alwayswannafly.di

import com.zxcursedsoundboard.apk.feature_alwayswannafly.data.FlyRepositoryImpl
import com.zxcursedsoundboard.apk.feature_alwayswannafly.domain.FlyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FlyModule {
    @Binds
    @Singleton
    abstract fun bindFlyRepository(
        flyRepositoryImpl: FlyRepositoryImpl
    ): FlyRepository
}