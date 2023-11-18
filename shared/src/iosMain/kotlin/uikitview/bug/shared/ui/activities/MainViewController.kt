package uikitview.bug.shared.ui.activities

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.window.ComposeUIViewController
import co.touchlab.kermit.Logger
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import platform.UIKit.UIView
import uikitview.bug.shared.domain.models.DrawerMenuItem
import uikitview.bug.shared.domain.models.enums.MenuItem
import uikitview.bug.shared.domain.utils.Constant
import uikitview.bug.shared.ui.components.NavigationDrawerScreen

class MainViewController {
    private data class ViewState(
        val menuItems: ImmutableList<DrawerMenuItem> = persistentListOf(),
        val selectedItem: DrawerMenuItem = DrawerMenuItem(
            code = MenuItem.COUNTRIES.code,
            isShownSearchBar = true,
            actionIcon = Icons.Filled.MoreVert,
        ),
    )

    private val state = MutableStateFlow(ViewState())

    @OptIn(ExperimentalForeignApi::class)
    fun statusComposable(initialView: UIView) =
        ComposeUIViewController {
            with(state.collectAsState().value) {
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val selectedDrawerItem = remember {
                    mutableStateOf(selectedItem)
                }
                val lastScreenAttached = remember {
                    mutableStateOf(selectedItem.code)
                }

                Logger.withTag(Constant.TAG_TRACING).d("Rendering Main View")

                NavigationDrawerScreen(
                    drawerState = drawerState,
                    scope = scope,
                    lastScreenAttached = lastScreenAttached,
                    menuItems = menuItems,
                    selectedItem = selectedDrawerItem,
                    content = {
                        Logger.withTag(Constant.TAG_COMPOSE).d("Rendering DrawerMenu content")
                        UIKitView(
                            factory = {
                                Logger.withTag(Constant.TAG_COMPOSE)
                                    .d("Rendering new view${selectedDrawerItem.value.code}")
                                initialView
                            },
                            interactive = true,
                            modifier = Modifier.fillMaxSize(),
                        )
                    },
                )
            }
        }

    fun updateStatusComposable(menuItems: ImmutableList<DrawerMenuItem>) {
        state.update {
            it.copy(menuItems = menuItems)
        }
    }
}
