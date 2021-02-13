package id.adeds.shared.data.repository

import id.adeds.shared.data.cache.`interface`.CharacterCacheInterface
import id.adeds.shared.data.model.toDomain
import id.adeds.shared.data.remote.CharacterRemoteInterface
import id.adeds.shared.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface CharacterRepository {
    suspend fun getCharacter(): Flow<List<Character>>
}

class CharacterRepositoryImpl(
    private val remoteInterface: CharacterRemoteInterface,
    private val cacheInterface: CharacterCacheInterface,
) :
    CharacterRepository {
    override suspend fun getCharacter(): Flow<List<Character>> {
        if (cacheInterface.getAllCharacter().isEmpty()) {
            val response = remoteInterface.getCharacter().toDomain()
            cacheInterface.saveCharacter(response)
        }
        return flow {
            emit(cacheInterface.getAllCharacter())
        }
    }

}