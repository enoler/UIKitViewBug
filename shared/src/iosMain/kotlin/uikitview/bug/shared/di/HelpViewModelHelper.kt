package uikitview.bug.shared.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uikitview.bug.shared.viewmodel.HelpViewModel

class HelpViewModelHelper : KoinComponent {
    private val viewModel: HelpViewModel by inject()
    fun get() = viewModel
}
