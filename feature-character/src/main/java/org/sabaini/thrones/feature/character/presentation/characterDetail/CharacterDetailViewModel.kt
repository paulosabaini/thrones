package org.sabaini.thrones.feature.character.presentation.characterDetail

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import org.sabaini.thrones.core.base.BaseViewModel
import org.sabaini.thrones.core.navigation.NavigationDestination
import org.sabaini.thrones.feature.character.domain.usecase.GetCharacterUseCase
import org.sabaini.thrones.feature.character.presentation.mapper.toPresentationModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    savedStateHandle: SavedStateHandle,
    charactersInitialState: CharacterDetailUiState
) : BaseViewModel<CharacterDetailUiState, CharacterDetailUiState.PartialState, CharacterDetailEvent, CharacterDetailIntent>(
    savedStateHandle,
    charactersInitialState
) {
    private val characterId: String = checkNotNull(
        savedStateHandle[NavigationDestination.CharacterDetail.navArgument]
    )

    init {
        acceptIntent(CharacterDetailIntent.GetCharacter)
    }

    override fun mapIntents(intent: CharacterDetailIntent): Flow<CharacterDetailUiState.PartialState> =
        when (intent) {
            is CharacterDetailIntent.GetCharacter -> getCharacter(characterId)
        }

    override fun reduceUiState(
        previousState: CharacterDetailUiState,
        partialState: CharacterDetailUiState.PartialState
    ): CharacterDetailUiState = when (partialState) {
        is CharacterDetailUiState.PartialState.Loading -> previousState.copy(
            isLoading = true,
            isError = false
        )
        is CharacterDetailUiState.PartialState.Fetched -> previousState.copy(
            isLoading = false,
            character = partialState.character,
            isError = false
        )
        is CharacterDetailUiState.PartialState.Error -> previousState.copy(
            isLoading = false,
            isError = true
        )
    }

    private fun getCharacter(characterId: String): Flow<CharacterDetailUiState.PartialState> = flow {
        getCharacterUseCase(characterId)
            .onStart {
                emit(CharacterDetailUiState.PartialState.Loading)
            }
            .collect { result ->
                result
                    .onSuccess { character ->
                        emit(
                            CharacterDetailUiState.PartialState.Fetched(
                                character.toPresentationModel()
                            )
                        )
                    }
                    .onFailure {
                        emit(CharacterDetailUiState.PartialState.Error(it))
                    }
            }
    }
}
