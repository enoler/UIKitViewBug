package uikitview.bug.shared.ui.activities

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import uikitview.bug.shared.domain.models.ListItem
import uikitview.bug.shared.ui.screen.HelpScreen

class HelpViewController {
    private data class ViewState(
        val sections: ImmutableList<ListItem> = persistentListOf(),
    )

    private val state = MutableStateFlow(ViewState())

    fun statusComposable(
        onClickSection: (section: ListItem) -> Unit,
    ) = ComposeUIViewController {
        with(state.collectAsState().value) {
            HelpScreen(
                sections = sections,
                onClickSection = onClickSection,
            )
        }
    }

    fun updateStatusComposable(
        sections: ImmutableList<ListItem>,
    ) {
        state.update {
            it.copy(sections = sections)
        }
    }
}
