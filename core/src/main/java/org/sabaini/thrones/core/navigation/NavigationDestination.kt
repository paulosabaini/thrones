package org.sabaini.thrones.core.navigation

sealed class NavigationDestination(
    val route: String,
    val navArgument: String = ""
) {
    object CharacterList : NavigationDestination("characterListDestination")

    object CharacterDetail : NavigationDestination(
        route = "characterDetailDestination",
        navArgument = "characterId"
    )
}
