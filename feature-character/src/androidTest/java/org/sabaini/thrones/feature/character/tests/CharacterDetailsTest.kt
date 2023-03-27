package org.sabaini.thrones.feature.character.tests

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.sabaini.thrones.feature.character.generateTestCharactersFromPresentation
import org.sabaini.thrones.feature.character.presentation.characterDetail.composable.CharacterDetails

class CharacterDetailsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testCharacter = generateTestCharactersFromPresentation().first()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            CharacterDetails(character = testCharacter)
        }
    }

    @Test
    fun characterDetails_shouldHaveCharacterFullNameDisplayed() {
        composeTestRule
            .onAllNodesWithText(testCharacter.fullName)
            .assertCountEquals(1)
    }
}
