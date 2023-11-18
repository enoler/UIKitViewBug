package uikitview.bug.shared.di

import org.koin.core.context.startKoin
import org.koin.dsl.module
import uikitview.bug.shared.domain.utils.AppContext
import uikitview.bug.shared.viewmodel.HelpViewModel
import uikitview.bug.shared.viewmodel.MainViewModel

actual val viewModelModule = module {
    factory { HelpViewModel() }
    factory { MainViewModel() }
}

actual fun initKoin(appContext: AppContext?) = startKoin {
    modules(allModules)
}
