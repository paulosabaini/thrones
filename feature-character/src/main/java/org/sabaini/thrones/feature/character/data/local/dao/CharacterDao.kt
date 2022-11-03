package org.sabaini.thrones.feature.character.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.sabaini.thrones.feature.character.data.local.model.CharacterCached
import org.sabaini.thrones.feature.character.data.remote.model.CharacterResponse

@Dao
interface CharacterDao {

    @Query("SELECT * FROM CharacterCached")
    fun getCharacters(): Flow<List<CharacterCached>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacters(characters: List<CharacterCached>)

    @Query("SELECT * FROM CharacterCached WHERE id = :characterId")
    fun getCharacter(characterId: Int): Flow<CharacterCached>
}