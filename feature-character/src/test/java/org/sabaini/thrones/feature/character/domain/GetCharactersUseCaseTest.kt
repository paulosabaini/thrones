package org.sabaini.thrones.feature.character.domain

import app.cash.turbine.test
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.sabaini.thrones.feature.character.domain.repository.CharacterRepository
import org.sabaini.thrones.feature.character.domain.usecase.GetCharactersUseCase
import org.sabaini.thrones.feature.character.domain.usecase.getCharacters
import org.sabaini.thrones.feature.character.generateTestCharacterFromDomain
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException
import kotlin.test.assertEquals

class GetCharactersUseCaseTest {

    @RelaxedMockK
    private lateinit var characterRepository: CharacterRepository

    private lateinit var objectUnderTest: GetCharactersUseCase

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        setUpGetCharactersUseCase()
    }

    @Test
    fun `should wrap result with success if repository doesn't throw`() = runTest {
        // Given
        val testCharactersFromDomain = listOf(generateTestCharacterFromDomain())
        coEvery { characterRepository.getCharacters() } returns flowOf(testCharactersFromDomain)

        // When-Then
        objectUnderTest.invoke().test {
            val result = awaitItem()

            assertEquals(
                expected = Result.success(testCharactersFromDomain),
                actual = result
            )
            awaitComplete()
        }
    }

    @Test
    fun `should retry operation if repository throws IOException`() = runTest {
        // Given
        val testException = IOException("Test message")
        val testCharactersFromDomain = listOf(generateTestCharacterFromDomain())
        coEvery { characterRepository.getCharacters() } throws testException andThen flowOf(
            testCharactersFromDomain
        )

        // When-Then
        assertThrows<IOException> {
            objectUnderTest.invoke().test {
                val errorResult = awaitItem()

                assertEquals(
                    expected = Result.failure(testException),
                    actual = errorResult
                )

                val itemsResult = awaitItem()

                assertEquals(
                    expected = Result.success(testCharactersFromDomain),
                    actual = itemsResult
                )
            }
        }
    }

    @Test
    fun `should rethrow if repository throws CancellationException`() = runTest {
        // Given
        coEvery { characterRepository.getCharacters() } throws CancellationException()

        // When-Then
        assertThrows<CancellationException> {
            objectUnderTest.invoke()
        }
    }

    @Test
    fun `should wrap result with failure if repository throws other Exception`() = runTest {
        // Given
        val testException = Exception("Test message")
        coEvery { characterRepository.getCharacters() } throws testException

        // When-Then
        assertThrows<Exception> {
            objectUnderTest.invoke().test {
                val result = awaitItem()

                assertEquals(
                    expected = Result.failure(testException),
                    actual = result
                )
            }
        }
    }

    private fun setUpGetCharactersUseCase() {
        objectUnderTest = GetCharactersUseCase {
            getCharacters(characterRepository)
        }
    }
}