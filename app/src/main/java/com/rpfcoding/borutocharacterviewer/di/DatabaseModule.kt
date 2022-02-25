package com.rpfcoding.borutocharacterviewer.di

import android.content.Context
import androidx.room.Room
import com.rpfcoding.borutocharacterviewer.data.local.BorutoDatabase
import com.rpfcoding.borutocharacterviewer.data.local.dao.HeroDao
import com.rpfcoding.borutocharacterviewer.data.local.dao.HeroRemoteKeyDao
import com.rpfcoding.borutocharacterviewer.data.repository.HeroRemoteKeyRepositoryImpl
import com.rpfcoding.borutocharacterviewer.data.repository.HeroRepositoryImpl
import com.rpfcoding.borutocharacterviewer.domain.repository.HeroRemoteKeyRepository
import com.rpfcoding.borutocharacterviewer.domain.repository.HeroRepository
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
    ): HeroRepository = HeroRepositoryImpl(heroDao)

    @Provides
    @Singleton
    fun provideHeroRemoteKeyRepository(
        heroRemoteKeyDao: HeroRemoteKeyDao
    ): HeroRemoteKeyRepository = HeroRemoteKeyRepositoryImpl(heroRemoteKeyDao)

}