package uikitview.bug.shared.viewmodel

import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import uikitview.bug.shared.domain.models.DrawerMenuItem
import uikitview.bug.shared.domain.models.enums.MenuItem

class MainViewModel: ViewModel() {

    var menuItems = MutableLiveData<ImmutableList<DrawerMenuItem>>(persistentListOf())

    fun getInitialScreen() =
        menuItems.value.find {
            it.code == MenuItem.COUNTRIES.code
        } ?: menuItems.value.first {
            it.isFragment
        }

    fun getMenuItems() {
        val items = arrayListOf(
            DrawerMenuItem(code = MenuItem.SETTINGS_CATEGORY.code, isFragment = false),
            DrawerMenuItem(
                code = MenuItem.HELP.code,
                title = "Help",
                image = MenuItem.HELP.image,
            ),
        )

        menuItems.value = items.toImmutableList()
    }
}
