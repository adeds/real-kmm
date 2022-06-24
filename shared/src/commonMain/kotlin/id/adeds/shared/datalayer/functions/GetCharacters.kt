package id.adeds.shared.datalayer.functions

import id.adeds.shared.datalayer.Repository
import id.adeds.shared.datalayer.sources.remote.characters.fetchCharacters
import id.adeds.shared.datalayer.sources.remote.debugLogger
import id.adeds.shared.viewmodel.characterslist.CharactersListItem

suspend fun Repository.getCountriesListData(): List<CharactersListItem> {

    if (Repository.charactersList.isEmpty()) {
        webservices.fetchCharacters()?.apply {
            if (info == null) {
                debugLogger.log("failed fetch data")
            } else {
                Repository.charactersList = results
            }
        }
    }

    return Repository.charactersList.map {  CharactersListItem(_data = it) }.toList()
}