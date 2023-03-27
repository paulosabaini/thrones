package org.sabaini.thrones.feature.character.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import org.sabaini.thrones.feature.character.data.local.model.CharacterCached

@Dao
interface CharacterDao {

    @Query("SELECT * FROM CharacterCached")
    fun getCharacters(): Flow<List<CharacterCached>>

    @Upsert
    suspend fun saveCharacters(characters: List<CharacterCached>)

    @Query("SELECT * FROM CharacterCached WHERE id = :characterId")
    fun getCharacter(characterId: String): Flow<CharacterCached>
}
