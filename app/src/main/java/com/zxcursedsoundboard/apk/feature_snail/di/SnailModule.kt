package com.zxcursedsoundboard.apk.feature_snail.di

import com.zxcursedsoundboard.apk.feature_snail.data.SnailRepositoryImpl
import com.zxcursedsoundboard.apk.feature_snail.domain.SnailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SnailModule {
    @Binds
    @Singleton
    abstract fun bindSnailRepository(
        snailRepositoryImpl: SnailRepositoryImpl
    ): SnailRepository
}