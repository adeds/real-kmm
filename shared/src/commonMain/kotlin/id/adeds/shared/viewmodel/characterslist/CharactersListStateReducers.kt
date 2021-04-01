package id.adeds.shared.viewmodel.characterslist

import id.adeds.shared.StateReducers
import id.adeds.shared.datalayer.functions.getCountriesListData

class CharactersListStateReducers(private val stateReducers: StateReducers) {

    fun restoreSelectedMenuItem(): MenuItem {
        //debugLogger.log("START restoreSelectedMenuItem")
        // restore the selected MenuItem saved in Settings into the state object
        val savedSelectedMenuItem = stateReducers.dataRepository.localSettings.selectedMenuItem
        stateReducers.stateManager.updateScreen(CharactersListState::class) {
            it.copy(selectedMenuItem = savedSelectedMenuItem)
        }
        return savedSelectedMenuItem
    }

    suspend fun updateCountriesList(menuItem: MenuItem) {
        // update CountriesListState, after retrieving the countries list from the repository
        var listData = stateReducers.dataRepository.getCountriesListData()
        if (menuItem == MenuItem.FAVORITES) {
            // in case the Favorites tab has been selected, only get the favorite countries
            listData = listData.filter {
                    stateReducers.dataRepository.localSettings.favoriteCharacters.containsKey(
                        it.id.toString()
                    )
                }
        }
        stateReducers.stateManager.updateScreen(CharactersListState::class) {
            it.copy(
                charactersListItems = listData,
                selectedMenuItem = menuItem,
                isLoading = false,
//            favoriteCharacters = dataRepository.localSettings.favoriteCountries,
            )
        }
        // save the MenuItem again into the Settings (as a "side-effect")
        stateReducers.dataRepository.localSettings.selectedMenuItem = menuItem
    }


    fun toggleFavorite(id: String) {
        // update the favorites map and save it into the state object
        val favoriteCountries = stateReducers.dataRepository.localSettings.favoriteCharacters
        if (favoriteCountries.containsKey(id)) {
            favoriteCountries.remove(id)
        } else {
            favoriteCountries[id] = true
        }
        stateReducers.stateManager.updateScreen(CharactersListState::class) {
            it.copy(favoriteCharacters = favoriteCountries)
        }
        // save the favoriteCountries map again into the Settings (as a "side-effect")
        stateReducers.dataRepository.localSettings.favoriteCharacters = favoriteCountries
    }
}