package uikitview.bug.shared.domain.models

import androidx.compose.ui.graphics.vector.ImageVector
import dev.icerock.moko.resources.ImageResource

data class DrawerMenuItem(
    val code: String,
    val image: ImageResource? = null,
    val title: String? = null,
    val isFragment: Boolean = true,
    val isShownSearchBar: Boolean = false,
    val actionIcon: ImageVector? = null,
)
