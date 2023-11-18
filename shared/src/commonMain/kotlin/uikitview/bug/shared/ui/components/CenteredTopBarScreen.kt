package uikitview.bug.shared.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import uikitview.bug.shared.ui.theme.Primary
import uikitview.bug.shared.ui.theme.TitleLargeRegular

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenteredTopBarOrSearchScreen(
    leftIcon: ImageVector = Icons.Filled.ArrowBack,
    screenTitle: String,
    onNavigationClick: () -> Unit,
    content: @Composable ((padding: PaddingValues) -> Unit),
) = Scaffold(
    topBar = {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Primary,
                titleContentColor = Primary,
            ),
            title = {
                TitleLargeRegular(
                    text = screenTitle,
                    color = Color.White,
                )
            },
            navigationIcon = {
                LeftIcon(
                    icon = leftIcon,
                    color = Color.White,
                    onNavigationClick = onNavigationClick,
                )
            },
        )
    },
) { innerPadding ->
    content(innerPadding)
}

@Composable
private fun LeftIcon(
    icon: ImageVector?,
    color: Color,
    contentDescription: String = "Action",
    onNavigationClick: () -> Unit,
) = icon?.let {
    IconButton(onClick = onNavigationClick) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = color,
        )
    }
}