package id.adeds.shared.domain.interactor.character

import id.adeds.shared.data.repository.CharacterRepository
import id.adeds.shared.domain.interactor.FlowUseCase
import id.adeds.shared.domain.interactor.listFlow
import id.adeds.shared.domain.model.Character
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCharactersUseCase(
    private val repository: CharacterRepository,
    backgroundThread: CoroutineDispatcher
) : FlowUseCase<Character, Unit>(backgroundThread) {
    override fun execute(params: Unit?): Flow<Character> =
        if (params == null) listFlow { repository.getCharacter() }
        else throw UnsupportedOperationException("getCharacter don't need param")
}