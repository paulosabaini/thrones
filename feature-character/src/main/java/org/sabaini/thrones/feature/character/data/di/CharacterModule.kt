package org.sabaini.thrones.feature.character.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sabaini.thrones.feature.character.data.remote.api.ThronesApi
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

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {

        @Binds
        @Singleton
        fun bindCharacterRepository()
    }
}