package org.sabaini.thrones.feature.character

import org.sabaini.thrones.feature.character.data.remote.model.CharacterResponse
import org.sabaini.thrones.feature.character.domain.model.Character

internal fun generateTestCharacterFromRemote() = CharacterResponse(
    id = 0,
    firstName = "Daenerys",
    lastName = "Targaryen",
    fullName = "Daenerys Targaryen",
    title = "Mother of Dragons",
    family = "House Targaryen",
    image = "daenerys.jpg",
    imageUrl = "https://thronesapi.com/assets/images/daenerys.jpg",
)

internal fun generateTestCharacterFromDomain() = Character(
    id = 0,
    firstName = "Daenerys",
    lastName = "Targaryen",
    fullName = "Daenerys Targaryen",
    title = "Mother of Dragons",
    family = "House Targaryen",
    imageUrl = "https://thronesapi.com/assets/images/daenerys.jpg",
)
