package uikitview.bug.shared.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import uikitview.bug.MR
import uikitview.bug.shared.domain.models.DrawerMenuItem
import uikitview.bug.shared.domain.utils.Constant
import uikitview.bug.shared.ui.theme.Accent
import uikitview.bug.shared.ui.theme.ITEM_HORIZONTAL_PADDING
import uikitview.bug.shared.ui.theme.ITEM_VERTICAL_PADDING
import uikitview.bug.shared.ui.theme.LabelLarge
import uikitview.bug.shared.ui.theme.Primary
import uikitview.bug.shared.ui.theme.TitleSmall

@Composable
fun NavigationDrawerScreen(
    drawerState: DrawerState,
    scope: CoroutineScope,
    lastScreenAttached: MutableState<String>,
    menuItems: ImmutableList<DrawerMenuItem>,
    selectedItem: MutableState<DrawerMenuItem>,
    content: @Composable () -> Unit,
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        scrimColor = DrawerDefaults.scrimColor,
        drawerContent = {
            DrawerContent(
                drawerState = drawerState,
                scope = scope,
                lastScreenAttached = lastScreenAttached,
                menuItems = menuItems,
                selectedItem = selectedItem,
            )
        },
        content = {
            ActionBar(
                drawerState = drawerState,
                scope = scope,
                content = content,
            )
        },
    )
}

@Composable
private fun ActionBar(
    drawerState: DrawerState,
    scope: CoroutineScope,
    content: @Composable () -> Unit,
) = CenteredTopBarOrSearchScreen(
    leftIcon = Icons.Filled.Menu,
    screenTitle = stringResource(MR.strings.app_name),
    onNavigationClick = {
        scope.launch {
            if (drawerState.isClosed) {
                drawerState.open()
            } else {
                drawerState.close()
            }
        }
    },
    content = { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            content()
        }
    },
)

@Composable
private fun DrawerContent(
    drawerState: DrawerState,
    scope: CoroutineScope,
    lastScreenAttached: MutableState<String>,
    menuItems: ImmutableList<DrawerMenuItem>,
    selectedItem: MutableState<DrawerMenuItem>,
) = ModalDrawerSheet(modifier = Modifier.width(275.dp)) {
    LazyColumn {
        items(menuItems.size) {
            val item = menuItems[it]
            if (item.image == null) {
                val index = menuItems.indexOf(item)
                if (index != 0) {
                    Divider(
                        modifier = Modifier.padding(
                            horizontal = ITEM_HORIZONTAL_PADDING.dp,
                            vertical = ITEM_VERTICAL_PADDING.dp,
                        ),
                    )
                }
                item.title?.let { title ->
                    TitleSmall(
                        text = title.uppercase(),
                        color = Primary,
                        modifier = Modifier.padding(
                            start = 25.dp,
                            bottom = ITEM_VERTICAL_PADDING.dp,
                        ),
                    )
                }
            } else {
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            painter = painterResource(item.image),
                            tint = Color.Gray,
                            contentDescription = null,
                            modifier = Modifier.width(24.dp),
                        )
                    },
                    label = {
                        LabelLarge(
                            text = item.title!!,
                            color = Accent,
                        )
                    },
                    selected = item.code == lastScreenAttached.value,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                        if (item.isFragment) {
                            selectedItem.value = item
                            Logger.withTag(Constant.TAG_COMPOSE).d("Changing screen")
                            lastScreenAttached.value = item.code
                        }
                    },
                    modifier = Modifier
                        .padding(NavigationDrawerItemDefaults.ItemPadding)
                        .height(45.dp),
                )
            }
        }
    }
}
