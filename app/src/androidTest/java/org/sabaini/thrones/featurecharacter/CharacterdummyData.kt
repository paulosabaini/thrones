package org.sabaini.thrones.featurecharacter

import org.sabaini.thrones.feature.character.domain.model.Character

internal val testCharactersFromDomain = listOf(
    Character(
        id = 0,
        firstName = "Daenerys",
        lastName = "Targaryen",
        fullName = "Daenerys Targaryen",
        title = "Mother of Dragons",
        family = "House Targaryen",
        imageUrl = "https://thronesapi.com/assets/images/daenerys.jpg"
    ),
    Character(
        id = 1,
        firstName = "Samwell",
        lastName = "Tarly",
        fullName = "Samwell Tarly",
        title = "Maester",
        family = "House Tarly",
        imageUrl = "https://thronesapi.com/assets/images/sam.jpg\n"
    ),
    Character(
        id = 2,
        firstName = "Jon",
        lastName = "Snow",
        fullName = "Jon Snow",
        title = "King of the North",
        family = "House Stark",
        imageUrl = "https://thronesapi.com/assets/images/jon-snow.jpg\n"
    )
)
