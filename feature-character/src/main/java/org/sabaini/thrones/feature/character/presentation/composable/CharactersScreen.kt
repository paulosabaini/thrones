package org.sabaini.thrones.feature.character.presentation.composable

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.Flow
import org.sabaini.thrones.core.extensions.collectWithLifecycle
import org.sabaini.thrones.feature.character.R
import org.sabaini.thrones.feature.character.presentation.CharactersEvent
import org.sabaini.thrones.feature.character.presentation.CharactersIntent
import org.sabaini.thrones.feature.character.presentation.CharactersUiState
import org.sabaini.thrones.feature.character.presentation.CharactersViewModel

@Composable
fun CharactersRoute(
    viewModel: CharactersViewModel = hiltViewModel()
) {
    HandleEvents(viewModel.event)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CharactersScreen(
        uiState = uiState,
        onRefreshCharacters = {
            viewModel.acceptIntent(CharactersIntent.RefreshCharacters)
        },
        onCharacterClicked = {
            viewModel.acceptIntent(CharactersIntent.CharacterClicked(it))
        }
    )
}

@Composable
fun CharactersScreen(
    uiState: CharactersUiState,
    onRefreshCharacters: () -> Unit,
    modifier: Modifier = Modifier,
    onCharacterClicked: (String) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(modifier = modifier, snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = uiState.isLoading),
            onRefresh = onRefreshCharacters,
            indicatorPadding = padding
        ) {
            if (uiState.characters.isNotEmpty()) {
                CharactersAvailableContent(
                    snackbarHostState = snackbarHostState,
                    uiState = uiState,
                    onCharacterClicked = onCharacterClicked
                )
            } else {
                CharactersNotAvailableContent(uiState = uiState)
            }
        }
    }
}

@Composable
private fun HandleEvents(events: Flow<CharactersEvent>) {
    events.collectWithLifecycle {
        when (it) {
            is CharactersEvent.ExampleEvent -> {}
        }
    }
}

@Composable
private fun CharactersAvailableContent(
    snackbarHostState: SnackbarHostState,
    uiState: CharactersUiState,
    onCharacterClicked: (String) -> Unit
) {
    if (uiState.isError) {
        val errorMessage = stringResource(R.string.characters_error_refreshing)

        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar(
                message = errorMessage
            )
        }
    }

    CharactersListContent(
        characterList = uiState.characters,
        onCharacterClicked = { onCharacterClicked(it) }
    )
}

@Composable
private fun CharactersNotAvailableContent(uiState: CharactersUiState) {
    when {
        uiState.isLoading -> CharactersLoadingPlaceHolder()
        uiState.isError -> CharactersErrorContent()
    }
}
