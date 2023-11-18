package uikitview.bug.shared.domain.models

import dev.icerock.moko.resources.ImageResource

data class ListItem(
    val code: String,
    val image: ImageResource? = null,
    val title: String,
    val subtitle: String? = null,
    val url: String? = null,
    var isChecked: Boolean = false,
    val onClick: ((item: ListItem) -> Unit)? = null,
)
