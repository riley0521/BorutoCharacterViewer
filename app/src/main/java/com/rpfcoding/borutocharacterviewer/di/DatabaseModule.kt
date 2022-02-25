package com.rpfcoding.borutocharacterviewer.di

import android.content.Context
import androidx.room.Room
import com.rpfcoding.borutocharacterviewer.data.local.BorutoDatabase
import com.rpfcoding.borutocharacterviewer.data.local.dao.HeroDao
import com.rpfcoding.borutocharacterviewer.data.local.dao.HeroRemoteKeyDao
import com.rpfcoding.borutocharacterviewer.data.repository.LocalHeroRemoteKeyRepositoryImpl
import com.rpfcoding.borutocharacterviewer.data.repository.LocalHeroRepositoryImpl
import com.rpfcoding.borutocharacterviewer.domain.repository.LocalHeroRemoteKeyRepository
import com.rpfcoding.borutocharacterviewer.domain.repository.LocalHeroRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        BorutoDatabase::class.java,
        "boruto_database"
    ).build()

    @Provides
    @Singleton
    fun provideHeroDao(
        db: BorutoDatabase
    ) = db.heroDao()

    @Provides
    @Singleton
    fun provideHeroRemoteKeyDao(
        db: BorutoDatabase
    ) = db.heroRemoteKeyDao()

    @Provides
    @Singleton
    fun provideHeroRepository(
        heroDao: HeroDao
    ): LocalHeroRepository = LocalHeroRepositoryImpl(heroDao)

    @Provides
    @Singleton
    fun provideHeroRemoteKeyRepository(
        heroRemoteKeyDao: HeroRemoteKeyDao
    ): LocalHeroRemoteKeyRepository = LocalHeroRemoteKeyRepositoryImpl(heroRemoteKeyDao)

}