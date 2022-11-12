package org.sabaini.thrones.feature.character.presentation.characterList.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import org.sabaini.thrones.core.ui.Typography
import org.sabaini.thrones.feature.character.R
import org.sabaini.thrones.feature.character.presentation.model.CharacterDisplayable

@Composable
fun CharacterItem(
    character: CharacterDisplayable,
    modifier: Modifier = Modifier,
    onCharacterClicked: () -> Unit
) {
    Card(
        modifier = modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_medium)),
        onClick = { onCharacterClicked() }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = character.imageUrl,
                contentDescription = stringResource(id = R.string.character_image_content_description),
                modifier = Modifier
                    .weight(1f)
                    .padding(dimensionResource(id = R.dimen.dimen_medium))
            )

            Text(
                text = character.fullName,
                style = Typography.titleMedium,
                modifier = Modifier.weight(2f)
            )
        }
    }
}
