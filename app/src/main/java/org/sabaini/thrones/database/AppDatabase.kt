package org.sabaini.thrones.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.sabaini.thrones.feature.character.data.local.dao.CharacterDao
import org.sabaini.thrones.feature.character.data.local.model.CharacterCached

private const val DATABASE_VERSION = 1

@Database(
    entities = [CharacterCached::class],
    version = DATABASE_VERSION,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}
