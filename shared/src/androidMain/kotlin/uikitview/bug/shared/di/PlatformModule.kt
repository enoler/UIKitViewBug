package uikitview.bug.shared.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import uikitview.bug.shared.domain.utils.AppContext
import uikitview.bug.shared.viewmodel.HelpViewModel
import uikitview.bug.shared.viewmodel.MainViewModel

actual val viewModelModule = module {
    viewModel { HelpViewModel() }
    viewModel { MainViewModel() }
}

actual fun initKoin(appContext: AppContext?) = startKoin {
    androidContext(appContext!!)
    modules(allModules)
}
