package org.sabaini.thrones.feature.character.presentation.mapper

import org.sabaini.thrones.feature.character.domain.model.Character
import org.sabaini.thrones.feature.character.presentation.model.CharacterDisplayable

fun Character.toPresentationModel() = CharacterDisplayable(
    id = id,
    firstName = firstName,
    lastName = lastName,
    fullName = fullName,
    title = title,
    family = family,
    imageUrl = imageUrl
)
