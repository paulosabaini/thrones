package org.sabaini.thrones.feature.character.presentation.characterDetail

sealed class CharacterDetailEvent {
    data class ExampleEvent(val value: String) : CharacterDetailEvent()
}
