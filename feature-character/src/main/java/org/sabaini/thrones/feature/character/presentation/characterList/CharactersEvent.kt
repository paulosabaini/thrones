package org.sabaini.thrones.feature.character.presentation.characterList

sealed class CharactersEvent {
    data class ExampleEvent(val value: String) : CharactersEvent()
}
