package org.sabaini.thrones.feature.character.tests

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.sabaini.thrones.feature.character.R
import org.sabaini.thrones.feature.character.generateTestCharactersFromPresentation
import org.sabaini.thrones.feature.character.presentation.characterDetail.CharacterDetailUiState
import org.sabaini.thrones.feature.character.presentation.characterDetail.composable.CHARACTER_LOADING_TEST_TAG
import org.sabaini.thrones.feature.character.presentation.characterDetail.composable.CharacterDetailScreen

class CharacterDetailScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val testCharacter = generateTestCharactersFromPresentation().first()

    private lateinit var errorFetchingMessage: String

    @Before
    fun setUp() {
        with(composeTestRule.activity) {
            errorFetchingMessage = getString(R.string.character_detail_error_fetching)
        }
    }

    @Test
    fun characterDetailScreen_whenContentNotAvailableAndLoadingOccurs_shouldHaveLoadingContent() {
        setUpComposable(
            CharacterDetailUiState(isLoading = true)
        )

        composeTestRule
            .onAllNodesWithTag(CHARACTER_LOADING_TEST_TAG)
            .assertCountEquals(1)
    }

    @Test
    fun characterDetailScreen_whenContentNotAvailableAndErrorOccurs_shouldHaveErrorContent() {
        setUpComposable(
            CharacterDetailUiState(isError = true)
        )

        composeTestRule
            .onNodeWithText(errorFetchingMessage)
            .assertExists()
    }

    private fun setUpComposable(
        characterDetailUiState: CharacterDetailUiState
    ) {
        composeTestRule.setContent { CharacterDetailScreen(uiState = characterDetailUiState) }
    }
}
