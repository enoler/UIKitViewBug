package uikitview

import android.app.Application
import uikitview.bug.shared.di.initKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
        application = this
    }

    private fun startKoin() = initKoin(this@App)

    companion object {
        private var application: Application? = null
    }
}
