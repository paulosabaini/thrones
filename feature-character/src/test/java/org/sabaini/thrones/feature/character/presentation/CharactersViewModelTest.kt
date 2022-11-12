package org.sabaini.thrones.feature.character.presentation

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.sabaini.thrones.core.utils.MainDispatcherExtension
import org.sabaini.thrones.feature.character.domain.usecase.GetCharactersUseCase
import org.sabaini.thrones.feature.character.domain.usecase.RefreshCharactersUseCase
import org.sabaini.thrones.feature.character.generateTestCharacterFromDomain
import org.sabaini.thrones.feature.character.presentation.characterList.CharactersIntent
import org.sabaini.thrones.feature.character.presentation.characterList.CharactersUiState
import org.sabaini.thrones.feature.character.presentation.characterList.CharactersViewModel
import org.sabaini.thrones.feature.character.presentation.mapper.toPresentationModel
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CharactersViewModelTest {

    @JvmField
    @RegisterExtension
    val mainDispatcherExtension = MainDispatcherExtension()

    @RelaxedMockK
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    // there is some issue with mocking functional interface with kotlin.Result(Unit)
    private val refreshCharactersUseCase: RefreshCharactersUseCase = RefreshCharactersUseCase {
        Result.failure(IllegalStateException("Test error"))
    }

    @SpyK
    private var savedStateHandle = SavedStateHandle()

    private lateinit var objectUnderTest: CharactersViewModel

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should show loading state with no error state first during init characters retrieval`() =
        runTest {
            // Given
            every { getCharactersUseCase() } returns emptyFlow()
            setUpCharactersViewModel()

            // When
            // init

            // Then
            objectUnderTest.uiState.test {
                val actualItem = awaitItem()

                assertTrue(actualItem.isLoading)
                assertFalse(actualItem.isError)
            }
        }

    @Test
    fun `should show fetched characters with no loading & error state during init characters retrieval success`() =
        runTest {
            // Given
            val testCharactersFromDomain = listOf(generateTestCharacterFromDomain())
            val testCharactersToPresentation =
                testCharactersFromDomain.map { it.toPresentationModel() }
            every { getCharactersUseCase() } returns flowOf(
                Result.success(testCharactersFromDomain)
            )
            setUpCharactersViewModel()

            // When
            // init

            // Then
            objectUnderTest.uiState.test {
                val actualItem = awaitItem()

                assertEquals(
                    expected = testCharactersToPresentation,
                    actual = actualItem.characters
                )
                assertFalse(actualItem.isLoading)
                assertFalse(actualItem.isError)
            }
        }

    @Test
    fun `should show error state with no loading state during init characters retrieval failure`() =
        runTest {
            // Given
            every { getCharactersUseCase() } returns flowOf(
                Result.failure(IllegalStateException("Test error"))
            )
            setUpCharactersViewModel()

            // When
            // init

            // Then
            objectUnderTest.uiState.test {
                val actualItem = awaitItem()

                assertTrue(actualItem.isError)
                assertFalse(actualItem.isLoading)
            }
        }

    @Test
    fun `should show error state with previously fetched characters during characters refresh failure`() =
        runTest {
            // Given
            val testCharactersFromDomain = listOf(generateTestCharacterFromDomain())
            val testCharactersToPresentation =
                testCharactersFromDomain.map { it.toPresentationModel() }
            every { getCharactersUseCase() } returns flowOf(
                Result.success(testCharactersFromDomain)
            )
            setUpCharactersViewModel()

            // When
            objectUnderTest.acceptIntent(CharactersIntent.RefreshCharacters)

            // Then
            objectUnderTest.uiState.test {
                val actualItem = awaitItem()

                assertTrue(actualItem.isError)
                assertEquals(
                    expected = testCharactersToPresentation,
                    actual = actualItem.characters
                )
            }
        }

    private fun setUpCharactersViewModel(
        initialUiState: CharactersUiState = CharactersUiState()
    ) {
        objectUnderTest = CharactersViewModel(
            getCharactersUseCase,
            refreshCharactersUseCase,
            savedStateHandle,
            initialUiState
        )
    }
}
