package org.sabaini.thrones.feature.character.domain

import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.just
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.sabaini.thrones.feature.character.domain.repository.CharacterRepository
import org.sabaini.thrones.feature.character.domain.usecase.RefreshCharactersUseCase
import org.sabaini.thrones.feature.character.domain.usecase.refreshCharacters
import kotlin.coroutines.cancellation.CancellationException
import kotlin.test.assertEquals

class RefreshCharactersUseCaseTest {
    @RelaxedMockK
    private lateinit var characterRepository: CharacterRepository

    private lateinit var objectUnderTest: RefreshCharactersUseCase

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        setUpRefreshCharactersUseCase()
    }

    @Test
    fun `should wrap result with success if repository doesn't throw`() = runTest {
        // Given
        coEvery { characterRepository.refreshCharacters() } just Runs

        // When
        val result = objectUnderTest.invoke()

        // Then
        assertEquals(
            expected = Result.success(Unit),
            actual = result
        )
    }

    @Test
    fun `should rethrow if repository throws CancellationException`() = runTest {
        // Given
        coEvery { characterRepository.refreshCharacters() } throws CancellationException()

        // When-Then
        assertThrows<CancellationException> {
            objectUnderTest.invoke()
        }
    }

    @Test
    fun `should wrap result with failure if repository throws other Throwable`() = runTest {
        // Given
        val testException = Throwable("Test message")
        coEvery { characterRepository.refreshCharacters() } throws testException

        // When-Then
        assertThrows<Throwable> {
            val result = objectUnderTest.invoke()

            assertEquals(
                expected = Result.failure(testException),
                actual = result
            )
        }
    }

    private fun setUpRefreshCharactersUseCase() {
        objectUnderTest = RefreshCharactersUseCase {
            refreshCharacters(characterRepository)
        }
    }
}
