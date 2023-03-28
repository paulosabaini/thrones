package org.sabaini.thrones.feature.character.data

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.flow.flowOf
import org.sabaini.thrones.core.extensions.resultOf
import org.sabaini.thrones.feature.character.data.di.CharacterModule
import org.sabaini.thrones.feature.character.data.remote.api.ThronesApi
import org.sabaini.thrones.feature.character.domain.usecase.GetCharacterUseCase
import org.sabaini.thrones.feature.character.domain.usecase.GetCharacterUseCaseImpl
import org.sabaini.thrones.feature.character.domain.usecase.GetCharactersUseCase
import org.sabaini.thrones.feature.character.domain.usecase.RefreshCharactersUseCase
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CharacterModule::class]
)
object FakeCharacterModule {

    @Provides
    @Singleton
    fun provideFakeThronesApi(
        retrofit: Retrofit
    ): ThronesApi {
        return retrofit.create(ThronesApi::class.java)
    }

    @Provides
    fun provideFakeGetCharactersUseCase(): GetCharactersUseCase {
        return GetCharactersUseCase {
            flowOf(
                Result.success(generateTestCharactersFromDomain())
            )
        }
    }

    @Provides
    fun provideNoopRefreshCharactersUseCase(): RefreshCharactersUseCase {
        return RefreshCharactersUseCase { resultOf { } }
    }

    @Provides
    fun provideFakeGetCharacterUseCase(): GetCharacterUseCase {
        return GetCharacterUseCaseImpl(FakeCharacterRepository())
    }
}
