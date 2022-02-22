package com.rpfcoding.borutocharacterviewer.di

import android.content.Context
import com.rpfcoding.borutocharacterviewer.data.pref.MyPreferencesRepositoryImpl
import com.rpfcoding.borutocharacterviewer.domain.repository.MyPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMyPreferencesRepository(
        @ApplicationContext context: Context
    ): MyPreferencesRepository = MyPreferencesRepositoryImpl(context = context)

}