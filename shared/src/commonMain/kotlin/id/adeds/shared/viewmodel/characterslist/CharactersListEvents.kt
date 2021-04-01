package id.adeds.shared.viewmodel.characterslist

import id.adeds.shared.Events

/********** INTERNAL EVENT FUNCTION, USED BY THE STATE PROVIDER **********/
class CharactersListEvents(private val events: Events) {
    private val charactersListStateReducers = CharactersListStateReducers(events.stateReducers)
    internal fun loadCountriesListData() {
        val restoredSelectedMenuItem = charactersListStateReducers.restoreSelectedMenuItem()
        //debugLogger.log("restoredSelectedMenuItem: "+restoredSelectedMenuItem)
        // launch a coroutine, as "updateCountriesList" is a suspend function
        events.launchCoroutine {
            charactersListStateReducers.updateCountriesList(restoredSelectedMenuItem)
        }
    }


    /********** PUBLIC EVENT FUNCTIONS **********/

    fun selectMenuItem(menuItem: MenuItem) {
        // launch a coroutine, as "updateCountriesList" is a suspend function
        events.launchCoroutine {
            charactersListStateReducers.updateCountriesList(menuItem)
        }
    }

    fun selectFavorite(country: String) {
        charactersListStateReducers.toggleFavorite(country)
    }
}