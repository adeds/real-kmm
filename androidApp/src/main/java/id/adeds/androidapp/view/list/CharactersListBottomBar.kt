package id.adeds.androidapp.view.list

import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import id.adeds.shared.viewmodel.characterslist.MenuItem

@Composable
fun CharactersListBottomBar(selectedItem : MenuItem, onItemClick: (MenuItem) -> Unit){
    BottomAppBar(content = {
        BottomNavigationItem(
            selected = selectedItem == MenuItem.ALL,
            onClick = { onItemClick(MenuItem.ALL) },
            icon = { Icon(imageVector = Icons.Default.Menu, contentDescription = "ALL") }
        )
    }) 
}