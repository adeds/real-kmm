package id.adeds.shared.data.cache.`interface`

import com.squareup.sqldelight.ColumnAdapter
import id.adeds.shared.data.cache.Character as CharacterTable
import id.adeds.shared.data.cache.DatabaseDriverFactory
import id.adeds.shared.data_cache.sqldelight.AppDatabase
import id.adeds.shared.domain.model.Character
import id.adeds.shared.domain.model.Gender
import id.adeds.shared.domain.model.Gender.*
import id.adeds.shared.domain.model.Status
import id.adeds.shared.domain.model.Status.*
import id.adeds.shared.domain.model.Status.UNKNOWN

interface CharacterCacheInterface {
    suspend fun saveCharacters(results: List<Character>)
    suspend fun updateCharacter(character: Character)
    suspend fun getAllCharacter() : List<Character>
}

class CharacterCacheInterfaceImpl(
    databaseDriverFactory: DatabaseDriverFactory
) : CharacterCacheInterface {

    private val statusAdapter = object : ColumnAdapter<Status, String> {
        override fun decode(databaseValue: String): Status = when (databaseValue) {
            "Alive" -> ALIVE
            "Dead" -> DEAD
            else -> UNKNOWN
        }

        override fun encode(value: Status): String = when (value) {
            ALIVE -> "Alive"
            DEAD -> "Dead"
            UNKNOWN -> "Unknown"
        }
    }

    private val genderAdapter = object : ColumnAdapter<Gender, String> {
        override fun decode(databaseValue: String): Gender = when (databaseValue) {
            "Male" -> MALE
            "Female" -> FEMALE
            "Genderless" -> GENDERLESS
            else -> Gender.UNKNOWN
        }

        override fun encode(value: Gender): String = when (value) {
            MALE -> "Male"
            FEMALE -> "Female"
            GENDERLESS -> "Genderless"
            Gender.UNKNOWN -> "Unknown"
        }
    }
    private val database =
        AppDatabase.invoke(
            databaseDriverFactory.createDriver(),
            CharacterTable.Adapter(statusAdapter, genderAdapter)
        )
    private val dbQuery = database.appDatabaseQueries

    override suspend fun saveCharacters(results: List<Character>) {
        dbQuery.transaction {
            results.forEach {
                saveCharacter(it)
            }
        }
    }

    override suspend fun updateCharacter(character: Character) {
        dbQuery.transaction { dbQuery.updateCharacter(
            character.id.toLong(),
            character.name,
            character.status,
            character.species,
            character.gender,
            character.origin,
            character.location,
            character.image,
            character.isFavorite) }
    }

    private fun saveCharacter(it: Character) {
        dbQuery.insertCharacter(
            it.id.toLong(),
            it.name,
            it.status,
            it.species,
            it.gender,
            it.origin,
            it.location,
            it.image,
            it.isFavorite
        )
    }

    override suspend fun getAllCharacter() =
        dbQuery.selectAllCharacter(::mapFavorite).executeAsList()


    private fun mapFavorite(
        id: Long,
        name: String,
        status: Status,
        species: String,
        gender: Gender,
        origin: String,
        location: String,
        image: String,
        isFavorite: Boolean,
    ): Character = Character(id.toInt(), name, status, species, gender, origin, location, image, isFavorite)
}