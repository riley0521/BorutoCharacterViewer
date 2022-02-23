package com.rpfcoding.borutocharacterviewer.di

import android.content.Context
import com.rpfcoding.borutocharacterviewer.data.repository.MyPreferencesRepositoryImpl
import com.rpfcoding.borutocharacterviewer.data.repository.Repository
import com.rpfcoding.borutocharacterviewer.domain.repository.MyPreferencesRepository
import com.rpfcoding.borutocharacterviewer.domain.use_cases.on_boarding.OnBoardingUseCases
import com.rpfcoding.borutocharacterviewer.domain.use_cases.on_boarding.ReadOnBoardingUseCase
import com.rpfcoding.borutocharacterviewer.domain.use_cases.on_boarding.SaveOnBoardingUseCase
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

    @Provides
    @Singleton
    fun provideOnBoardingUseCases(
        repository: Repository
    ): OnBoardingUseCases {
        return OnBoardingUseCases(
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository = repository),
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository = repository)
        )
    }

}