package org.sabaini.thrones.feature.character.presentation.characterList

sealed class CharactersIntent {
    object GetCharacters : CharactersIntent()
    object RefreshCharacters : CharactersIntent()
    data class CharacterClicked(val id: String) : CharactersIntent()
}
