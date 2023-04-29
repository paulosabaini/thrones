package org.sabaini.thrones.feature.character.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import org.sabaini.thrones.feature.character.data.local.dao.CharacterDao
import org.sabaini.thrones.feature.character.data.mapper.toDomainModel
import org.sabaini.thrones.feature.character.data.mapper.toEntityModel
import org.sabaini.thrones.feature.character.data.remote.api.ThronesApi
import org.sabaini.thrones.feature.character.domain.model.Character
import org.sabaini.thrones.feature.character.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val thronesApi: ThronesApi,
    private val characterDao: CharacterDao,
) : CharacterRepository {

    override fun getCharacters(): Flow<List<Character>> {
        return characterDao
            .getCharacters()
            .map { charactersCached ->
                charactersCached.map { it.toDomainModel() }
            }
            .onEach { rockets ->
                if (rockets.isEmpty()) {
                    refreshCharacters()
                }
            }
    }

    override suspend fun refreshCharacters() {
        thronesApi
            .getCharacters()
            .map {
                it.toDomainModel().toEntityModel()
            }
            .also {
                characterDao.saveCharacters(it)
            }
    }

    override fun getCharacter(characterId: String): Flow<Character> {
        return characterDao
            .getCharacter(characterId)
            .map { it.toDomainModel() }
    }
}
