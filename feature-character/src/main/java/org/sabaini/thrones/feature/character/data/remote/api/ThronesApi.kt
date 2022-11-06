package org.sabaini.thrones.feature.character.data.remote.api

import org.sabaini.thrones.feature.character.data.remote.model.CharacterResponse
import retrofit2.http.GET

interface ThronesApi {

    @GET("characters")
    suspend fun getCharacters(): List<CharacterResponse>
}
