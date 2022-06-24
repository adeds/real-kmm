package id.adeds.shared.datalayer.sources.remote.characters

import id.adeds.shared.datalayer.sources.remote.ApiClient
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

suspend fun ApiClient.fetchCharacters(): CharactersResponse? {
    return getResponse("/character?page=1")
}


@Serializable
data class CharactersResponse(
    @SerialName("results") val results: List<CharactersListData>,
    @SerialName("info") val info: InfoResponse? = null,
)

@Serializable
data class InfoResponse(
    @SerialName("pages") val pages: Int
)

@Serializable
data class CharactersListData(
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = "",
    @SerialName("status") val status: String = "",
    @SerialName("species") val species: String = "",
    @SerialName("gender") val genderString: String = "",
    @SerialName("image") val image: String = "",
) {
    // in the DataLayer classes, the computed properties don't do any UI-formatting operation
    // they just process data, including any arithmetical operation
    val gender: Gender
        get() = parseGender(genderString)

    private fun parseGender(value: String): Gender {
        return when (value) {
            "Male" -> Gender.MALE
            "Female" -> Gender.FEMALE
            else -> Gender.OTHERS
        }
    }
}

sealed class Gender {
    object MALE : Gender()
    object FEMALE : Gender()
    object OTHERS : Gender()
}