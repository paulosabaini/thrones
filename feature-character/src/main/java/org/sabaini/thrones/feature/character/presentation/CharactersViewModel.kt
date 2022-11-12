package org.sabaini.thrones.feature.character.presentation

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import org.sabaini.thrones.core.base.BaseViewModel
import org.sabaini.thrones.core.navigation.NavigationCommands
import org.sabaini.thrones.core.navigation.NavigationManager
import org.sabaini.thrones.feature.character.domain.usecase.GetCharactersUseCase
import org.sabaini.thrones.feature.character.domain.usecase.RefreshCharactersUseCase
import org.sabaini.thrones.feature.character.presentation.mapper.toPresentationModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val refreshCharactersUseCase: RefreshCharactersUseCase,
    private val navigationManager: NavigationManager,
    savedStateHandle: SavedStateHandle,
    charactersInitialState: CharactersUiState
) : BaseViewModel<CharactersUiState, CharactersUiState.PartialState, CharactersEvent, CharactersIntent>(
    savedStateHandle,
    charactersInitialState
) {
    init {
        acceptIntent(CharactersIntent.GetCharacters)
    }

    override fun mapIntents(intent: CharactersIntent): Flow<CharactersUiState.PartialState> =
        when (intent) {
            is CharactersIntent.GetCharacters -> getCharacters()
            is CharactersIntent.RefreshCharacters -> refreshCharacters()
            is CharactersIntent.CharacterClicked -> characterClicked(intent.id)
        }

    override fun reduceUiState(
        previousState: CharactersUiState,
        partialState: CharactersUiState.PartialState
    ): CharactersUiState = when (partialState) {
        is CharactersUiState.PartialState.Loading -> previousState.copy(
            isLoading = true,
            isError = false
        )
        is CharactersUiState.PartialState.Fetched -> previousState.copy(
            isLoading = false,
            characters = partialState.list,
            isError = false
        )
        is CharactersUiState.PartialState.Error -> previousState.copy(
            isLoading = false,
            isError = true
        )
    }

    private fun getCharacters(): Flow<CharactersUiState.PartialState> = flow {
        getCharactersUseCase()
            .onStart {
                emit(CharactersUiState.PartialState.Loading)
            }
            .collect { result ->
                result
                    .onSuccess { characterList ->
                        emit(
                            CharactersUiState.PartialState.Fetched(
                                characterList.map { it.toPresentationModel() }
                            )
                        )
                    }
                    .onFailure {
                        emit(CharactersUiState.PartialState.Error(it))
                    }
            }
    }

    private fun refreshCharacters(): Flow<CharactersUiState.PartialState> = flow {
        refreshCharactersUseCase()
            .onFailure {
                emit(CharactersUiState.PartialState.Error(it))
            }
    }

    private fun characterClicked(id: String): Flow<CharactersUiState.PartialState> {
        navigationManager.navigate(NavigationCommands.CharactersScreen.charactersScreenToDetailScreen())
        return emptyFlow()
    }
}
