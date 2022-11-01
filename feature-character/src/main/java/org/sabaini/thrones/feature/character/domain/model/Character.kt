package org.sabaini.thrones.feature.character.domain.model

data class Character(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val fullName: String,
    val title: String,
    val family: String,
    val imageUrl: String
)
