package org.sabaini.thrones.feature.character.domain.usecase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retryWhen
import org.sabaini.thrones.core.extensions.resultOf
import org.sabaini.thrones.feature.character.domain.repository.CharacterRepository
import org.sabaini.thrones.feature.character.domain.model.Character
import java.io.IOException

private const val RETRY_TIME_IN_MILLIS = 15_000L

fun interface GetCharactersUseCase : () -> Flow<Result<List<Character>>>

fun getCharacters(
    characterRepository: CharacterRepository
): Flow<Result<List<Character>>> =
    characterRepository
        .getCharacters()
        .map {
            resultOf { it }
        }
        .retryWhen { cause, _ ->
            if (cause is IOException) {
                emit(Result.failure(cause))

                delay(RETRY_TIME_IN_MILLIS)
                true
            } else {
                false
            }
        }
        .catch { // for other than IOException but it will stop collecting Flow
            emit(Result.failure(it))
        }
