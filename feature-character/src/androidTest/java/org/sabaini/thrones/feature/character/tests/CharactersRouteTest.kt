package org.sabaini.thrones.feature.character.tests

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.sabaini.thrones.core.MainActivity
import org.sabaini.thrones.core.navigation.AppBarState
import org.sabaini.thrones.core.utils.getHiltTestViewModel
import org.sabaini.thrones.feature.character.data.generateTestCharactersFromDomain
import org.sabaini.thrones.feature.character.presentation.characterList.composable.CharactersRoute

@HiltAndroidTest
class CharactersRouteTest {

    @get:Rule(order = 0)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val testCharacters = generateTestCharactersFromDomain()

    @Before
    fun setUp() {
        hiltTestRule.inject()
        composeTestRule.activity.setContent {
            CharactersRoute(
                onAppBarState = { AppBarState(title = "Thrones") },
                viewModel = composeTestRule.getHiltTestViewModel()
            )
        }
    }

    @Test
    fun charactersRoute_whenHappyPath_shouldShowAllFakeCharacters() {
        testCharacters.forEach { character ->
            composeTestRule
                .onNodeWithText(character.fullName)
                .assertExists()
        }
    }
}
