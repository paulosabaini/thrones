package org.sabaini.thrones.feature.character.presentation

sealed class CharactersEvent {
    data class OpenWebBrowserWithDetails(val uri: String) : CharactersEvent()
}
