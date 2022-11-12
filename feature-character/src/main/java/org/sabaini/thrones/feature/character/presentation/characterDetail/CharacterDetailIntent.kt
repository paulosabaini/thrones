package org.sabaini.thrones.feature.character.presentation.characterDetail

sealed class CharacterDetailIntent {
    object GetCharacter : CharacterDetailIntent()
}
