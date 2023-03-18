package com.zxcursedsoundboard.feature_favourite.di

import com.zxcursedsoundboard.feature_favourite.data.FavouriteRepositoryImpl
import com.zxcursedsoundboard.feature_favourite.domain.repository.FavouriteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FavouriteRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindFavouriteRepository(
        favoriteRepositoryImpl: FavouriteRepositoryImpl
    ): FavouriteRepository
}