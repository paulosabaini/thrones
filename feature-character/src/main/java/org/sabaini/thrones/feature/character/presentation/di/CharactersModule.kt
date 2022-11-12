package org.sabaini.thrones.feature.character.presentation.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import org.sabaini.thrones.core.navigation.NavigationFactory
import org.sabaini.thrones.feature.character.presentation.CharactersNavigationFactory
import org.sabaini.thrones.feature.character.presentation.CharactersUiState
import org.sabaini.thrones.feature.character.presentation.characterDetail.CharacterDetailUiState
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object CharactersViewModelModule {

    @Provides
    fun provideInitialCharactersUiState(): CharactersUiState = CharactersUiState()
}

@Module
@InstallIn(ViewModelComponent::class)
object CharacterDetailViewModelModule {

    @Provides
    fun provideInitialCharacterDetailUiState(): CharacterDetailUiState = CharacterDetailUiState()
}

@Module
@InstallIn(SingletonComponent::class)
interface CharactersSingletonModule {

    @Singleton
    @Binds
    @IntoSet
    fun bindCharactersNavigationFactory(factory: CharactersNavigationFactory): NavigationFactory
}
