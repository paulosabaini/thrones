package org.sabaini.thrones.feature.character.presentation

sealed class CharactersEvent {
    data class ExampleEvent(val value: String) : CharactersEvent()
}
