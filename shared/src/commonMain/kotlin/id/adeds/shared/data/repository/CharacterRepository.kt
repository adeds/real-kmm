package id.adeds.shared.data.repository

import id.adeds.shared.data.model.toDomain
import id.adeds.shared.data.remote.CharacterRemoteInterface
import id.adeds.shared.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacter(): List<Character>
}

class CharacterRepositoryImpl(private val remoteInterface: CharacterRemoteInterface) :
    CharacterRepository {
    override suspend fun getCharacter(): List<Character> {
        return remoteInterface.getCharacter().toDomain()
    }

}