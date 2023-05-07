package com.zxcursedsoundboard.apk.feature_sounds_zxcursed.di

import com.zxcursedsoundboard.apk.feature_sounds_zxcursed.data.ZxcursedSoundsRepositoryImpl
import com.zxcursedsoundboard.apk.feature_sounds_zxcursed.domain.ZxcursedSoundsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ZxcursedSoundsModule {

    @Binds
    @Singleton
    abstract fun bindZxcursedSounds(zxcursedSoundsRepositoryImpl: ZxcursedSoundsRepositoryImpl): ZxcursedSoundsRepository
}