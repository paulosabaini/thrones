package org.sabaini.thrones.feature.character.domain.repository

import kotlinx.coroutines.flow.Flow
import org.sabaini.thrones.feature.character.domain.model.Character

interface CharacterRepository {
    fun getCharacters(): Flow<List<Character>>
    fun getCharacter(): Flow<Character>
    suspend fun refreshCharacters()
}
