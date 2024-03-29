package com.zxcursedsoundboard.apk.feature_favourite.di

import android.content.Context
import androidx.room.Room
import com.zxcursedsoundboard.apk.feature_favourite.data.network.FavouriteDao
import com.zxcursedsoundboard.apk.feature_favourite.data.network.FavouriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavouriteModule {

    @Singleton
    @Provides
    fun provideFavouriteDatabase(@ApplicationContext context: Context): FavouriteDatabase {
        synchronized(this) {
            return Room.databaseBuilder(
                context,
                FavouriteDatabase::class.java,
                "favourite_Database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    @Singleton
    @Provides
    fun provideDao(database: FavouriteDatabase): FavouriteDao {
        return database.getDao()
    }
}