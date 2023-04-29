package org.sabaini.thrones.feature.character.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.sabaini.thrones.feature.character.domain.model.Character
import org.sabaini.thrones.feature.character.domain.repository.CharacterRepository

interface GetCharacterUseCase {
    suspend operator fun invoke(characterId: String): Flow<Result<Character>>
}

class GetCharacterUseCaseImpl(
    private val characterRepository: CharacterRepository,
) : GetCharacterUseCase {
    override suspend fun invoke(characterId: String) =
        characterRepository
            .getCharacter(characterId)
            .map {
                Result.success(it)
            }
            .catch {
                emit(Result.failure(it))
            }
}
