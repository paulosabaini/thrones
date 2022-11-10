package org.sabaini.thrones.feature.character.data

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.sabaini.thrones.feature.character.data.local.dao.CharacterDao
import org.sabaini.thrones.feature.character.data.mapper.toDomainModel
import org.sabaini.thrones.feature.character.data.mapper.toEntityModel
import org.sabaini.thrones.feature.character.data.remote.api.ThronesApi
import org.sabaini.thrones.feature.character.data.repository.CharacterRepositoryImpl
import org.sabaini.thrones.feature.character.domain.repository.CharacterRepository
import org.sabaini.thrones.feature.character.generateTestCharacterFromRemote

class CharacterRepositoryTest {

    @RelaxedMockK
    private lateinit var thronesApi: ThronesApi

    @RelaxedMockK
    private lateinit var characterDao: CharacterDao

    private lateinit var objectUnderTest: CharacterRepository

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        setUpCharacterRepository()
    }

    @Test
    fun `should refresh characters if local database is empty`() = runTest {
        // Given
        every { characterDao.getCharacters() } returns flowOf(emptyList())

        // When
        objectUnderTest.getCharacters().collect()

        // Then
        coVerifyOrder {
            thronesApi.getCharacters()
            characterDao.saveCharacters(any())
        }
    }

    @Test
    fun `should save mapped characters locally if retrieved from remote`() = runTest {
        // Given
        val testCharactersFromRemote = listOf(generateTestCharacterFromRemote())
        val testCharactersToCache =
            testCharactersFromRemote.map { it.toDomainModel().toEntityModel() }
        coEvery { thronesApi.getCharacters() } returns testCharactersFromRemote

        // When
        objectUnderTest.refreshCharacters()

        // Then
        coVerify { characterDao.saveCharacters(testCharactersToCache) }
    }

    private fun setUpCharacterRepository() {
        objectUnderTest = CharacterRepositoryImpl(
            thronesApi,
            characterDao
        )
    }
}
