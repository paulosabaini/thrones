package org.sabaini.thrones.feature.character.presentation.characterDetail

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

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
    modifier: Modifier = Modifier,
) {
    Text(text = "Character Detail Screen", style = MaterialTheme.typography.bodyLarge)
}