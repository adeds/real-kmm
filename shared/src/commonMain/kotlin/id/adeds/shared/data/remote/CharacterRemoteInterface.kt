package id.adeds.shared.data.remote

import id.adeds.shared.data.model.ApiCharactersResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

interface CharacterRemoteInterface {
    suspend fun getCharacter(): ApiCharactersResponse
}

class CharacterRemoteInterfaceImpl(
    private val endPoint: String,
    private val httpClient: HttpClient
) : CharacterRemoteInterface{
    override suspend fun getCharacter(): ApiCharactersResponse =
        httpClient.get { apiUrl("/api/character") }

    private fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            takeFrom(endPoint)
            encodedPath = path
        }
    }
}