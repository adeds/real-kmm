package id.adeds.shared.domain.interactor.character

import id.adeds.shared.data.repository.CharacterRepository
import id.adeds.shared.domain.interactor.FlowUseCase
import id.adeds.shared.domain.model.Character
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCase(
    private val repository: CharacterRepository,
    backgroundThread: CoroutineDispatcher
) : FlowUseCase<List<Character>, Unit>(backgroundThread) {
    override suspend fun execute(params: Unit?): Flow<List<Character>> = repository.getCharacter()
}