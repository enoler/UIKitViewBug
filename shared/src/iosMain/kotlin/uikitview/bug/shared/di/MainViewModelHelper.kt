package uikitview.bug.shared.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uikitview.bug.shared.viewmodel.MainViewModel

class MainViewModelHelper : KoinComponent {
    private val viewModel: MainViewModel by inject()
    fun get() = viewModel
}
