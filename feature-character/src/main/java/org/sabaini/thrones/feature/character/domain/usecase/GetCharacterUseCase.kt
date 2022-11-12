package org.sabaini.thrones.feature.character.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.sabaini.thrones.core.extensions.resultOf
import org.sabaini.thrones.feature.character.domain.model.Character
import org.sabaini.thrones.feature.character.domain.repository.CharacterRepository

fun interface GetCharacterUseCase : () -> Flow<Result<Character>>

fun getCharacter(
    characterRepository: CharacterRepository
): Flow<Result<Character>> =
    characterRepository
        .getCharacter()
        .map {
            resultOf { it }
        }
        .catch {
            emit(Result.failure(it))
        }
