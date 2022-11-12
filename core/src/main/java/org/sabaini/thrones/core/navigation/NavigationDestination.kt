package org.sabaini.thrones.core.navigation

sealed class NavigationDestination(
    val route: String
) {
    object CharacterList : NavigationDestination("characterListDestination")

    object CharacterDetail : NavigationDestination("characterDetailDestination")
}
