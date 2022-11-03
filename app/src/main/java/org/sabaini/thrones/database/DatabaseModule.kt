package org.sabaini.thrones.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sabaini.thrones.feature.character.data.local.dao.CharacterDao
import javax.inject.Singleton

private const val APP_DATABASE_NAME = "app_database_name"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            APP_DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideRocketDao(database: AppDatabase): CharacterDao {
        return database.characterDao()
    }
}

