package id.adeds.shared.domain.interactor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

abstract class FlowUseCase<out T, in Params> constructor(
    private val threadExecutor: CoroutineDispatcher
) {
    internal abstract suspend fun execute(params: Params? = null): Flow<T>
    open suspend operator fun invoke(params: Params? = null): Flow<T> =
        execute(params).flowOn(threadExecutor)
}

fun <R> listFlow(block: suspend () -> List<R>): Flow<R> = flow {
    emitAll(block().asFlow())
}