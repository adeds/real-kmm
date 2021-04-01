package id.adeds.shared.viewmodel.characterslist

import id.adeds.shared.StateProvider


class CharactersListStateProvider (private val stateProvider: StateProvider) {
    private val charactersListEvents = CharactersListEvents(stateProvider.events)
    fun getCountriesListState(): CharactersListState {

        // the state gets initialized with "initState":
        //      ONLY WHEN this function is called for the first time
        // after initialization, the "callOnInit" code gets called
        return stateProvider.stateManager.getScreen(
            initState = { CharactersListState(isLoading = true) },
            callOnInit = { charactersListEvents.loadCountriesListData() }
        )

    }
}