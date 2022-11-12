package org.sabaini.thrones.featurecharacter

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.sabaini.thrones.MainActivity
import org.sabaini.thrones.feature.character.presentation.characterList.composable.CharactersRoute
import org.sabaini.thrones.utils.getHiltTestViewModel

@HiltAndroidTest
class CharactersRouteTest {

    @get:Rule(order = 0)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltTestRule.inject()
        composeTestRule.activity.setContent {
            CharactersRoute(
                viewModel = composeTestRule.getHiltTestViewModel()
            )
        }
    }

    @Test
    fun charactersRoute_whenHappyPath_shouldShowAllFakeCharacters() {
        testCharactersFromDomain.forEach { character ->
            composeTestRule
                .onNodeWithText(character.fullName)
                .assertExists()
        }
    }
}
