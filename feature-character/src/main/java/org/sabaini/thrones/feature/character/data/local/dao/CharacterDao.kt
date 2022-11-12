package org.sabaini.thrones.feature.character.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.sabaini.thrones.feature.character.data.local.model.CharacterCached

@Dao
interface CharacterDao {

    @Query("SELECT * FROM CharacterCached")
    fun getCharacters(): Flow<List<CharacterCached>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacters(characters: List<CharacterCached>)

    @Query("SELECT * FROM CharacterCached LIMIT 1")
    fun getCharacter(): Flow<CharacterCached>
}
