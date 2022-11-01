package org.sabaini.thrones.feature.character.data.repository

import kotlinx.coroutines.flow.Flow
import org.sabaini.thrones.feature.character.data.local.dao.CharacterDao
import org.sabaini.thrones.feature.character.data.remote.api.ThronesApi
import org.sabaini.thrones.feature.character.domain.model.Character
import org.sabaini.thrones.feature.character.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val thronesApi: ThronesApi,
    private val characterDao: CharacterDao
): CharacterRepository {

    override fun getCharacters(): Flow<List<Character>> {
        TODO("Not yet implemented")
    }

    override fun getCharacter(): Flow<Character> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshCharacters() {
        TODO("Not yet implemented")
    }
}