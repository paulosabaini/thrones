package org.sabaini.thrones.feature.character.tests

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.sabaini.thrones.feature.character.data.generateTestCharactersFromPresentation
import org.sabaini.thrones.feature.character.presentation.characterList.composable.CHARACTER_DIVIDER_TEST_TAG
import org.sabaini.thrones.feature.character.presentation.characterList.composable.CharactersListContent

class CharactersListContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testCharacters = generateTestCharactersFromPresentation()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            CharactersListContent(
                characterList = testCharacters,
                onCharacterClicked = {},
            )
        }
    }

    @Test
    fun charactersListContent_shouldNotShowTheDividerAfterLastItem() {
        val expectedDividerCount = testCharacters.size - 1

        composeTestRule
            .onAllNodesWithTag(CHARACTER_DIVIDER_TEST_TAG)
            .assertCountEquals(expectedDividerCount)
    }
}
