package org.sabaini.thrones.feature.character.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sabaini.thrones.core.navigation.NavigationDestination
import org.sabaini.thrones.core.navigation.NavigationFactory
import org.sabaini.thrones.feature.character.presentation.composable.CharactersRoute
import javax.inject.Inject

class CharactersNavigationFactory @Inject constructor() : NavigationFactory {

    override fun create(builder: NavGraphBuilder) {
        builder.composable(NavigationDestination.CharacterList.route) {
            CharactersRoute()
        }
    }
}
