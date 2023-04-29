package org.sabaini.thrones.feature.character.data.mapper

import org.sabaini.thrones.feature.character.data.local.model.CharacterCached
import org.sabaini.thrones.feature.character.data.remote.model.CharacterResponse
import org.sabaini.thrones.feature.character.domain.model.Character

fun CharacterResponse.toDomainModel() = Character(
    id = id,
    firstName = firstName,
    lastName = lastName,
    fullName = fullName,
    title = title,
    family = family,
    imageUrl = imageUrl,
)

fun CharacterCached.toDomainModel() = Character(
    id = id,
    firstName = firstName,
    lastName = lastName,
    fullName = fullName,
    title = title,
    family = family,
    imageUrl = imageUrl,
)

fun Character.toEntityModel() = CharacterCached(
    id = id,
    firstName = firstName,
    lastName = lastName,
    fullName = fullName,
    title = title,
    family = family,
    imageUrl = imageUrl,
)
