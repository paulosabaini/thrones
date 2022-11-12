package org.sabaini.thrones.feature.character.presentation.characterList.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CharactersLoadingPlaceHolder(
    modifier: Modifier = Modifier
) {
    Spacer(
        modifier = modifier.fillMaxSize()
    )
}
