package id.adeds.shared.datalayer.sources.remote

import id.adeds.shared.DebugLogger
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.*
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

val debugLogger by lazy { DebugLogger("Real-KMM") }

class ApiClient {

    val baseUrl = "https://rickandmortyapi.com/api"

    val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json { ignoreUnknownKeys = true })
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }


    suspend inline fun <reified T:Any> getResponse(endpoint : String): T? {
        val url = baseUrl+endpoint
        try {
            // please notice, Ktor Client is switching to a background thread under the hood
            // so the http call doesn't happen on the main thread, even if the coroutine has been launched on Dispatchers.Main
            val resp = client.get<T>(url)
            debugLogger.log("$url API SUCCESS")
            return resp
        } catch (e: Exception) {
            debugLogger.log("$url API FAILED: "+e.message )
        }
        return null
    }


}
