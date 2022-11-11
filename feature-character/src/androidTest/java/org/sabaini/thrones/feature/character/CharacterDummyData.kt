package org.sabaini.thrones.feature.character

import org.sabaini.thrones.feature.character.presentation.model.CharacterDisplayable

internal fun generateTestCharactersFromPresentation() = listOf(
    CharacterDisplayable(
        id = 0,
        firstName = "Daenerys",
        lastName = "Targaryen",
        fullName = "Daenerys Targaryen",
        title = "Mother of Dragons",
        family = "House Targaryen",
        imageUrl = ""
    ),
    CharacterDisplayable(
        id = 1,
        firstName = "Samwell",
        lastName = "Tarly",
        fullName = "Samwell Tarly",
        title = "Maester",
        family = "House Tarly",
        imageUrl = ""
    ),
    CharacterDisplayable(
        id = 2,
        firstName = "Jon",
        lastName = "Snow",
        fullName = "Jon Snow",
        title = "King of the North",
        family = "House Stark",
        imageUrl = ""
    )
)
