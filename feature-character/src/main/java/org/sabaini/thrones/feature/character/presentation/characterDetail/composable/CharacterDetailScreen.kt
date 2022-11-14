package org.sabaini.thrones.feature.character.presentation.characterDetail.composable

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sabaini.thrones.feature.character.R
import org.sabaini.thrones.feature.character.presentation.characterDetail.CharacterDetailUiState
import org.sabaini.thrones.feature.character.presentation.characterDetail.CharacterDetailViewModel

@Composable
fun CharacterDetailRoute(
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CharacterDetailScreen(uiState = uiState)
}

@Composable
fun CharacterDetailScreen(
    uiState: CharacterDetailUiState,
    modifier: Modifier = Modifier
) {
    if (uiState.character != null) {
        CharacterDetails(character = uiState.character, modifier = modifier)
    } else {
        CharacterNotAvailableContent(uiState = uiState)
    }
}

@Composable
private fun CharacterNotAvailableContent(
    uiState: CharacterDetailUiState
) {
    when {
        uiState.isLoading -> {
            CircularProgressIndicator()
        }
        uiState.isError -> {
            Text(
                text = stringResource(id = R.string.character_detail_error_fetching),
                color = Color.Red,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}
