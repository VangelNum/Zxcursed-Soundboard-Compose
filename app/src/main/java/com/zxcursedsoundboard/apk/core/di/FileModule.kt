package com.zxcursedsoundboard.apk.core.di

import com.zxcursedsoundboard.apk.core.data.repository.FileRepositoryImpl
import com.zxcursedsoundboard.apk.core.domain.repository.FileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class FileModule {
    @Binds
    @Singleton
    abstract fun bindFileRepository(fileRepositoryImpl: FileRepositoryImpl): FileRepository
}