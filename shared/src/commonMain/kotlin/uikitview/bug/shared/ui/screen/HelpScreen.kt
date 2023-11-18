package uikitview.bug.shared.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import co.touchlab.kermit.Logger
import kotlinx.collections.immutable.ImmutableList
import uikitview.bug.shared.domain.models.ListItem
import uikitview.bug.shared.domain.utils.Constant
import uikitview.bug.shared.ui.components.ListItemWithIconAndSubtitle

@Composable
fun HelpScreen(
    sections: ImmutableList<ListItem>,
    onClickSection: (item: ListItem) -> Unit,
) {
    HelpUI(
        sections = sections,
        onClickSection = onClickSection,
    )
}

@Composable
fun HelpUI(
    sections: ImmutableList<ListItem>,
    onClickSection: (item: ListItem) -> Unit,
) = LazyColumn {
    Logger.withTag(Constant.TAG_COMPOSE).d("Rendering Help and Suggestions")
    items(sections.size) { index ->
        val section = sections[index]
        ListItemWithIconAndSubtitle(
            item = section,
            onClick = onClickSection,
        )
    }
}