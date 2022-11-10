package org.sabaini.thrones.featurecharacter

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.flow.flowOf
import org.sabaini.thrones.core.extensions.resultOf
import org.sabaini.thrones.feature.character.data.di.CharacterModule
import org.sabaini.thrones.feature.character.domain.usecase.GetCharactersUseCase
import org.sabaini.thrones.feature.character.domain.usecase.RefreshCharactersUseCase

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CharacterModule::class]
)
object FakeCharacterModule {

    @Provides
    fun provideFakeGetCharactersUseCase(): GetCharactersUseCase {
        return GetCharactersUseCase {
            flowOf(
                Result.success(testCharactersFromDomain)
            )
        }
    }

    @Provides
    fun provideNoopRefreshCharactersUseCase(): RefreshCharactersUseCase {
        return RefreshCharactersUseCase { resultOf { } }
    }
}
