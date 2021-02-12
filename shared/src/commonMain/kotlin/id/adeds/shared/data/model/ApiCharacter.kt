package id.adeds.shared.data.model

import id.adeds.shared.domain.model.Character
import id.adeds.shared.domain.model.Gender
import id.adeds.shared.domain.model.Status
import kotlinx.serialization.Serializable

@Serializable
data class ApiCharactersResponse(
    val results: List<ApiCharacter>
)

@Serializable
data class ApiCharacter(
    val id: Int?,
    val name: String?,
    val status: String?,
    val species: String?,
    val gender: String?,
    val origin: Location?,
    val location: Location?,
    val image: String?
)

@Serializable
data class Location(
    val name: String?
)

fun ApiCharactersResponse.toDomain(): List<Character> = results.map {
    Character(
        id = it.id ?: 0,
        name = it.name ?: "",
        status = it.status.toStatusCharacter(),
        species = it.species ?: "",
        gender = it.gender.toGenderCharacter(),
        origin = it.location?.name ?: "",
        location = it.location?.name ?: "",
        image = it.image ?: ""

    )
}

private fun String?.toGenderCharacter(): Gender {
    return when (this) {
        "Male" -> Gender.MALE
        "Female" -> Gender.FEMALE
        "Genderless" -> Gender.GENDERLESS
        else -> Gender.UNKNOWN
    }
}

private fun String?.toStatusCharacter(): Status {
    return when (this) {
        "Alive" -> Status.ALIVE
        "Dead" -> Status.DEAD
        else -> Status.UNKNOWN
    }
}
