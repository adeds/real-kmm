package id.adeds.androidapp.view.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.CoilImage
import id.adeds.shared.viewmodel.characterslist.CharactersListItem

@Composable
fun CountriesListRow(
    item: CharactersListItem,
    favorite: Boolean,
    onItemClick: () -> Unit,
    onFavoriteIconClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CoilImage(data = item.image, fadeIn = true, contentDescription = item.name)
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(weight = 1f, fill = true)
                .padding(20.dp, 5.dp)
        ) {
            Text(text = item.name)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = item.gender)

        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(100.dp)
                .clickable(onClick = onFavoriteIconClick),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row {
                if (favorite) {
                    Icon(Icons.Default.Star, contentDescription = "favorite", tint = Color.Magenta)
                } else {
                    Icon(
                        Icons.Default.StarBorder,
                        contentDescription = "not a favorite",
                        tint = Color.LightGray
                    )
                }
            }
        }
    }
    Divider()
}
