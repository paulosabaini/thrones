package org.sabaini.thrones.feature.character.presentation.characterList

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import org.sabaini.thrones.feature.character.presentation.model.CharacterDisplayable

@Immutable
@Parcelize
data class CharactersUiState(
    val isLoading: Boolean = false,
    val characters: List<CharacterDisplayable> = emptyList(),
    val isError: Boolean = false
) : Parcelable {

    sealed class PartialState {
        object Loading : PartialState()

        data class Fetched(val list: List<CharacterDisplayable>) : PartialState()

        data class Error(val throwable: Throwable) : PartialState()
    }
}
