package org.sabaini.thrones.core.navigation

object NavigationCommands {
    object CharactersScreen {
        fun charactersScreenToDetailScreen() = object : NavigationCommand {
            override val destination = NavigationDestination.CharacterDetail.route
        }
    }
}
