package org.sabaini.thrones.feature.character.domain.usecase

import org.sabaini.thrones.core.extensions.resultOf
import org.sabaini.thrones.feature.character.domain.repository.CharacterRepository

fun interface RefreshCharactersUseCase : suspend () -> Result<Unit>

suspend fun refreshCharacters(
    characterRepository: CharacterRepository
): Result<Unit> = resultOf {
    characterRepository.refreshCharacters()
}
