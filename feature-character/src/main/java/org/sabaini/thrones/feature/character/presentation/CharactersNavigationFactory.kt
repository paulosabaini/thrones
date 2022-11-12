package org.sabaini.thrones.feature.character.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.sabaini.thrones.core.navigation.NavigationDestination
import org.sabaini.thrones.core.navigation.NavigationFactory
import org.sabaini.thrones.feature.character.presentation.characterDetail.CharacterDetailRoute
import org.sabaini.thrones.feature.character.presentation.characterList.composable.CharactersRoute
import javax.inject.Inject

class CharactersNavigationFactory @Inject constructor() : NavigationFactory {

    override fun create(builder: NavGraphBuilder) {
        builder.composable(NavigationDestination.CharacterList.route) {
            CharactersRoute()
        }

        builder.composable(
            route = NavigationDestination.CharacterDetail.route
                .plus("/{")
                .plus(NavigationDestination.CharacterDetail.navArgument)
                .plus("}"),
            arguments = listOf(
                navArgument(
                    NavigationDestination.CharacterDetail.navArgument
                ) {
                    type = NavType.StringType
                }
            )
        ) {
            CharacterDetailRoute()
        }
    }
}
