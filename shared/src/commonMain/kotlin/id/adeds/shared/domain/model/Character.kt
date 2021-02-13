package id.adeds.shared.domain.model

data class Character(
    val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val gender: Gender,
    val origin: String,
    val location: String,
    val image: String
)

private fun Gender.toString(): String {
    return when (this) {
        Gender.MALE -> "Male"
        Gender.FEMALE -> "Female"
        Gender.GENDERLESS -> "Genderless"
        Gender.UNKNOWN -> "Unknown"
    }
}

private fun Status.toString(): String {
    return when (this) {
        Status.ALIVE -> "Alive"
        Status.DEAD -> "Dead"
        Status.UNKNOWN -> "Unknown"
    }
}
