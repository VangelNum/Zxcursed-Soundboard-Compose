package com.zxcursedsoundboard.apk.core.di

import android.annotation.SuppressLint
import android.app.Application
import androidx.media3.database.DatabaseProvider
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.cache.Cache
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExoPlayerModule {

    @SuppressLint("UnsafeOptInUsageError")
    @Provides
    @Singleton
    fun provideCache(app: Application): Cache {
        val cacheDirectory = File(app.cacheDir, "media_cache")
        val databaseProvider: DatabaseProvider = StandaloneDatabaseProvider(app)
        val cacheEvictor = LeastRecentlyUsedCacheEvictor(200 * 1024 * 1024)
        return SimpleCache(cacheDirectory, cacheEvictor, databaseProvider)
    }

    @SuppressLint("UnsafeOptInUsageError")
    @Provides
    @Singleton
    fun provideCacheDataSourceFactory(app: Application, cache: Cache): CacheDataSource.Factory {
        val upstreamFactory: DataSource.Factory = DefaultDataSource.Factory(app)
        return CacheDataSource.Factory()
            .setCache(cache)
            .setUpstreamDataSourceFactory(upstreamFactory)
            .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)
    }
}