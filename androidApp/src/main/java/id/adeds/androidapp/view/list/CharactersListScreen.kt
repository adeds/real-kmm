package id.adeds.androidapp.view.list


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.adeds.androidapp.ui.element.LoadingElement
import id.adeds.shared.viewmodel.characterslist.CharactersListEvents
import id.adeds.shared.viewmodel.characterslist.CharactersListState

@Composable
fun CountriesListScreen(charactersListState: CharactersListState, events : CharactersListEvents, onListItemClick: (String) -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "My Rick Morty Characters List",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            })
        },
        content = { paddingValues ->
            if (charactersListState.isLoading) {
                LoadingElement()
            } else {
                if (charactersListState.charactersListItems.isEmpty()) {
                    Text(
                        text = "empty list",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )
                } else {
                    LazyColumn(contentPadding = paddingValues) {
                        items(items = charactersListState.charactersListItems, itemContent = { item ->
                            CountriesListRow(
                                item = item,
                                favorite = charactersListState.favoriteCharacters.containsKey(item.name),
                                onItemClick = { onListItemClick(item.name) },
                                onFavoriteIconClick = { events.selectFavorite(item.name) })
                        })
                    }
                }
            }
        }/*,
        bottomBar = {
            CharactersListBottomBar(selectedItem = charactersListState.selectedMenuItem, onItemClick = { events.selectMenuItem(it) })
        }*/
    )
}