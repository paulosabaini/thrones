package org.sabaini.thrones.feature.character.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sabaini.thrones.core.navigation.AppBarState
import org.sabaini.thrones.core.navigation.NavigationDirections
import org.sabaini.thrones.core.navigation.NavigationFactory
import org.sabaini.thrones.feature.character.presentation.characterDetail.composable.CharacterDetailRoute
import org.sabaini.thrones.feature.character.presentation.characterList.composable.CharactersRoute
import javax.inject.Inject

class CharactersNavigationFactory @Inject constructor() : NavigationFactory {

    override fun create(builder: NavGraphBuilder, onAppBarState: (AppBarState) -> Unit) {
        builder.composable(NavigationDirections.CharacterList.destination) {
            CharactersRoute(onAppBarState)
        }

        builder.composable(
            route = NavigationDirections.CharacterDetailNavigation.ROUTE,
            arguments = NavigationDirections.CharacterDetailNavigation.argumentsList,
        ) {
            CharacterDetailRoute(onAppBarState)
        }
    }
}
