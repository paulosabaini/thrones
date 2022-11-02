package org.sabaini.thrones.feature.character.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import org.sabaini.thrones.feature.character.R
import org.sabaini.thrones.feature.character.presentation.model.CharacterDisplayable

@Composable
fun CharacterItem(
    character: CharacterDisplayable,
    modifier: Modifier = Modifier,
    onCharacterClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(
                vertical = dimensionResource(id = R.dimen.dimen_medium)
            )
            .clickable { onCharacterClicked() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = character.imageUrl,
            contentDescription = stringResource(id = R.string.character_image_content_description),
            modifier = Modifier.weight(1f)
        )

        Text(
            text = character.fullName,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.weight(2f)
        )
    }
}
