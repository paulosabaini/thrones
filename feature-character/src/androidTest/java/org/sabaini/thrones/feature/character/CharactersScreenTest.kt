package org.sabaini.thrones.feature.character

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.sabaini.thrones.feature.character.presentation.characterList.CharactersUiState
import org.sabaini.thrones.feature.character.presentation.characterList.composable.CharactersScreen

class CharactersScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val testCharacters = generateTestCharactersFromPresentation()

    private lateinit var characterContentDescription: String
    private lateinit var errorRefreshingMessage: String
    private lateinit var errorFetchingMessage: String

    @Before
    fun setUp() {
        with(composeTestRule.activity) {
            characterContentDescription = getString(R.string.character_image_content_description)
            errorRefreshingMessage = getString(R.string.characters_error_refreshing)
            errorFetchingMessage = getString(R.string.characters_error_fetching)
        }
    }

    @Test
    fun charactersScreen_whenContentAvailableAndErrorOccurs_shouldKeepContent() {
        setUpComposable(
            CharactersUiState(
                characters = testCharacters,
                isError = true
            )
        )

        composeTestRule
            .onAllNodesWithContentDescription(characterContentDescription)
            .assertCountEquals(testCharacters.size)
    }

    @Test
    fun charactersScreen_whenContentAvailableAndErrorOccurs_shouldShowErrorSnackbar() {
        setUpComposable(
            CharactersUiState(
                characters = testCharacters,
                isError = true
            )
        )

        composeTestRule
            .onNodeWithText(errorRefreshingMessage)
            .assertExists()
    }

    @Test
    fun charactersScreen_whenContentNotAvailableAndErrorOccurs_shouldHaveErrorContent() {
        setUpComposable(
            CharactersUiState(isError = true)
        )

        composeTestRule
            .onNodeWithText(errorFetchingMessage)
            .assertExists()
    }

    private fun setUpComposable(
        charactersUiState: CharactersUiState
    ) {
        composeTestRule.setContent {
            CharactersScreen(
                uiState = charactersUiState,
                onRefreshCharacters = {},
                onCharacterClicked = {}
            )
        }
    }
}
