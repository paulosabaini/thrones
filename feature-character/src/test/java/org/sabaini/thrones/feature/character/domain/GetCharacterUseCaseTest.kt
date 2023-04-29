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
import org.sabaini.thrones.feature.character.domain.usecase.GetCharacterUseCase
import org.sabaini.thrones.feature.character.domain.usecase.GetCharacterUseCaseImpl
import org.sabaini.thrones.feature.character.generateTestCharacterFromDomain
import kotlin.coroutines.cancellation.CancellationException
import kotlin.test.assertEquals

class GetCharacterUseCaseTest {

    @RelaxedMockK
    private lateinit var characterRepository: CharacterRepository

    private lateinit var objectUnderTest: GetCharacterUseCase

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        setUpGetCharactersUseCase()
    }

    @Test
    fun `should wrap result with success if repository doesn't throw`() = runTest {
        // Given
        val testCharacterFromDomain = generateTestCharacterFromDomain()
        coEvery { characterRepository.getCharacter(any()) } returns flowOf(testCharacterFromDomain)

        // When-Then
        objectUnderTest("0").test {
            val result = awaitItem()

            assertEquals(
                expected = Result.success(testCharacterFromDomain),
                actual = result,
            )
            awaitComplete()
        }
    }

    @Test
    fun `should rethrow if repository throws CancellationException`() = runTest {
        // Given
        coEvery { characterRepository.getCharacter(any()) } throws CancellationException()

        // When-Then
        assertThrows<CancellationException> {
            objectUnderTest("0")
        }
    }

    @Test
    fun `should wrap result with failure if repository throws other Exception`() = runTest {
        // Given
        val testException = Exception("Test message")
        coEvery { characterRepository.getCharacter("0") } throws testException

        // When-Then
        assertThrows<Exception> {
            objectUnderTest("0").test {
                val result = awaitItem()

                assertEquals(
                    expected = Result.failure(testException),
                    actual = result,
                )
            }
        }
    }

    private fun setUpGetCharactersUseCase() {
        objectUnderTest = GetCharacterUseCaseImpl(characterRepository)
    }
}
