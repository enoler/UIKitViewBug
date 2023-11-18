package uikitview.bug.shared.viewmodel

import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import uikitview.bug.shared.domain.models.ListItem
import uikitview.bug.shared.domain.models.enums.MenuItem

class HelpViewModel : ViewModel() {
    val sections = MutableLiveData<ImmutableList<ListItem>>(persistentListOf())

    fun fetchData() {
        val sectionsList = persistentListOf(
            ListItem(
                code = MenuItem.HELP_CONTACT.code,
                title = "Contact",
                subtitle = "Send me an email",
            )
        )
        sections.value = sectionsList
    }
}
