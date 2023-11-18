package uikitview.bug.shared.domain.models.enums

import dev.icerock.moko.resources.ImageResource
import uikitview.bug.MR

enum class MenuItem(val code: String, val image: ImageResource?) {
    COUNTRIES(code = "countries", image = MR.images.ic_explore),
    HELP(code = "help", image = MR.images.ic_email),
    SETTINGS_CATEGORY(code = "settingsCategory", image = null),
    HELP_CONTACT(code = "help_contact", image = null),
}
