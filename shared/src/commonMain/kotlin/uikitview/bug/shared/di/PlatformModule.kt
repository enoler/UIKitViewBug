package uikitview.bug.shared.di

import org.koin.core.KoinApplication
import org.koin.core.module.Module
import uikitview.bug.shared.domain.utils.AppContext

expect val viewModelModule: Module

expect fun initKoin(appContext: AppContext? = null): KoinApplication
