package org.sabaini.thrones.feature.character.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sabaini.thrones.feature.character.data.remote.api.ThronesApi
import org.sabaini.thrones.feature.character.data.repository.CharacterRepositoryImpl
import org.sabaini.thrones.feature.character.domain.repository.CharacterRepository
import org.sabaini.thrones.feature.character.domain.usecase.GetCharactersUseCase
import org.sabaini.thrones.feature.character.domain.usecase.RefreshCharactersUseCase
import org.sabaini.thrones.feature.character.domain.usecase.getCharacters
import org.sabaini.thrones.feature.character.domain.usecase.refreshCharacters
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [CharacterModule.BindsModule::class])
@InstallIn(SingletonComponent::class)
object CharacterModule {

    @Provides
    @Singleton
    fun provideThronesApi(
        retrofit: Retrofit
    ): ThronesApi {
        return retrofit.create(ThronesApi::class.java)
    }

    @Provides
    fun provideGetCharactersUseCase(
        characterRepository: CharacterRepository
    ): GetCharactersUseCase {
        return GetCharactersUseCase {
            getCharacters(characterRepository)
        }
    }

    @Provides
    fun provideRefreshCharactersUseCase(
        characterRepository: CharacterRepository
    ): RefreshCharactersUseCase {
        return RefreshCharactersUseCase {
            refreshCharacters(characterRepository)
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {

        @Binds
        @Singleton
        fun bindCharacterRepository(impl: CharacterRepositoryImpl): CharacterRepository
    }
}
