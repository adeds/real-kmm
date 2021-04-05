package id.adeds.shared.viewmodel.characterslist

import id.adeds.shared.ScreenState
import id.adeds.shared.datalayer.sources.remote.characters.CharactersListData
import id.adeds.shared.datalayer.sources.remote.characters.Gender


/********** STATE CLASS DEFINITION **********/

data class CharactersListState(
    val isLoading: Boolean = false,
    val selectedMenuItem: MenuItem = MenuItem.ALL,
    val charactersListItems: List<CharactersListItem> = emptyList(),
    val favoriteCharacters: Map<String, Boolean> = emptyMap(),
) : ScreenState


/********** PROPERTY CLASSES DEFINITION **********/


enum class MenuItem { ALL, FAVORITES }

class CharactersListItem(
    _data: CharactersListData,
) {
    // in the ViewModel classes, our computed properties only do UI-formatting operations
    // (the arithmetical operations, such as calculating a percentage, should happen in the DataLayer classes)
    val id : Int = _data.id
    val name = _data.name
    val gender = _data.gender.parseString()
    val image = _data.image
}

private fun Gender.parseString() = when (this) {
    Gender.MALE -> "Male"
    Gender.FEMALE -> "Female"
    Gender.OTHERS -> "Other"
}