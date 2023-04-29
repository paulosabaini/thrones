package org.sabaini.thrones.feature.character.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.sabaini.thrones.feature.character.domain.model.Character
import org.sabaini.thrones.feature.character.domain.repository.CharacterRepository

class FakeCharacterRepository : CharacterRepository {
    override fun getCharacters(): Flow<List<Character>> {
        return flowOf(
            generateTestCharactersFromDomain(),
        )
    }

    override suspend fun refreshCharacters() {}

    override fun getCharacter(characterId: String): Flow<Character> {
        return flowOf(
            generateTestCharactersFromDomain().first(),
        )
    }
}
