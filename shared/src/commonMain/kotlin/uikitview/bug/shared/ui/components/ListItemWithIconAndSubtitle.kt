package uikitview.bug.shared.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.painterResource
import uikitview.bug.shared.domain.models.ListItem
import uikitview.bug.shared.ui.theme.BodyLarge
import uikitview.bug.shared.ui.theme.BodyMedium
import uikitview.bug.shared.ui.theme.ITEM_HORIZONTAL_PADDING

@Composable
fun ListItemWithIconAndSubtitle(
    item: ListItem,
    onClick: ((item: ListItem) -> Unit) = {},
    modifier: Modifier = Modifier,
) {
    var customModifier = modifier
        .fillMaxWidth()
        .padding(ITEM_HORIZONTAL_PADDING.dp)
    customModifier = customModifier.clickable {
        onClick(item)
    }
    Box(modifier = customModifier) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            item.image?.let {
                Image(
                    painterResource(it),
                    contentDescription = item.title,
                    modifier = Modifier.width(35.dp),
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                BodyLarge(text = item.title)
                item.subtitle?.let {
                    BodyMedium(text = it)
                }
            }
        }
    }
}
