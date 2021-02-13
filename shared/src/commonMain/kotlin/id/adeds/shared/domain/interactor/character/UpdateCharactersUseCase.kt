package id.adeds.shared.domain.interactor.character

import id.adeds.shared.data.repository.CharacterRepository
import id.adeds.shared.domain.interactor.FlowUseCase
import id.adeds.shared.domain.model.Character
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class UpdateCharactersUseCase(
    private val repository: CharacterRepository,
    backgroundThread: CoroutineDispatcher
) : FlowUseCase<List<Character>, Character>(backgroundThread) {
    override suspend fun execute(params: Character?): Flow<List<Character>> {
        return if (params!=null)repository.updateCharacter(params)
        else throw UnsupportedOperationException("need character")
    }
}