package org.sabaini.thrones.core.navigation

object NavigationCommands {
    object CharactersScreen {
        fun charactersScreenToDetailScreen(
            characterId: String
        ) = object : NavigationCommand {
            override val destination = NavigationDestination.CharacterDetail.route
                .plus("/")
                .plus(characterId)
        }
    }
}
