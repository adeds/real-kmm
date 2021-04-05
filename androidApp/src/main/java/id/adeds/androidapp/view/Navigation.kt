package id.adeds.androidapp.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import id.adeds.androidapp.utils.Constant.Companion.CHARACTERS_LIST_SCREEN
import id.adeds.androidapp.view.list.CountriesListScreen
import id.adeds.shared.KMPViewModel
import id.adeds.shared.viewmodel.characterslist.CharactersListEvents
import id.adeds.shared.viewmodel.characterslist.CharactersListStateProvider

@Composable
fun Navigation(appViewModel: KMPViewModel) {

    val events = appViewModel.events
    val appState by appViewModel.stateFlow.collectAsState()

    val listProvider =
        CharactersListStateProvider(stateProvider = appState.getStateProvider(appViewModel))
    
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = CHARACTERS_LIST_SCREEN){
        composable(route = CHARACTERS_LIST_SCREEN){
            CountriesListScreen(
                charactersListState = listProvider.getCountriesListState(),
                events = CharactersListEvents(events = events),
            ) {
//                navController.navigate(it)
            }
        }
    }
}